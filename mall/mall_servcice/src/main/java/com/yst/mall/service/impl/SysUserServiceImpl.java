package com.yst.mall.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.mall.common.bean.SysUserResponse;
import com.yst.mall.common.bean.UserAddRequest;
import com.yst.mall.common.bean.UserUpdateRequest;
import com.yst.mall.common.data.PageRange;
import com.yst.mall.common.data.ResultData;
import com.yst.mall.dao.SysUserMapper;
import com.yst.mall.dao.SysUserRoleMapper;
import com.yst.mall.model.SysRole;
import com.yst.mall.model.SysUser;
import com.yst.mall.model.SysUserExample;
import com.yst.mall.model.SysUserRole;
import com.yst.mall.service.SysUserService;
import com.yst.mall.util.layui.CheckUtil;
import com.yst.mall.util.layui.Constants;
import com.yst.mall.util.layui.PasswordUtil;
import com.yst.mall.util.layui.SHA1;
import com.yst.mall.util.layui.UUIDUtil;

@Service
public class SysUserServiceImpl implements SysUserService{
	private final Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Override
	public ResultData<SysUser> loginDo(SysUserExample example) {
		ResultData<SysUser> result=new ResultData<SysUser>();
		/*List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
		if(sysUserList!=null&&sysUserList.size()>0){
			SysUser user = sysUserList.get(0);
			if(sysUserList.get(0).getState()==1){
				result.setCode(ResultData.SUCCESS);
				result.setData(user);
			}else if(user.getState()==2){
				result.setCode(ResultData.CHECK_FAIL);
				result.setMsg("用户被锁定！");
			}else if(user.getState()==3){
				result.setCode(ResultData.CHECK_FAIL);
				result.setMsg("用户被禁用");
			}
		}else{
			result.setCode(ResultData.CHECK_FAIL);
			result.setMsg("用户名密码错误");
		}*/
		return result;
	}
    /**
     * 获取用户角色列表
     * @param userId 用户id
     * */
	@Override
    public List<String> getRoleNames(String userId){
        return sysUserRoleMapper.getRoleNamesByUserId(userId);
    }
    /**
     * 根据登录名获取用户
     * @param loginName 登录名
     * */
	@Override
    public SysUser getByLoginName(String loginName){
		return sysUserMapper.getByLoginName(loginName);
    }
	/**
     * 验证用户
     * @param loginName 登录名
     * @param password 密码（为了不增加复杂度，这里不进行加密，使用明文）
     * */
    public Boolean validateUser(String loginName,String password){
        int n = sysUserMapper.countUserByNameAndPwd(loginName,password);
        return n>0?true:false;
    }



    /**
     * 分页获取用户列表
     *
     * @param page         页序
     * @param limit        分页大小
     * @param loginName     登录名
     * @param realName     姓名
     * @param status       状态 0-禁用 1-启用
     */
    public List<SysUser> getAll(String page, String limit, String loginName, String realName, String status) {
        PageRange pageRange = new PageRange(page, limit);
        return sysUserMapper.getAll(pageRange.getStart(), pageRange.getEnd(), loginName, realName, status);
    }

    /**
     * 获取用户列表大小
     *
     * @param loginName     登录名
     * @param realName     姓名
     * @param status       状态 0-禁用 1-启用
     */
    public int countGetAll(String loginName, String realName, String status) {
        return sysUserMapper.countGetAll(loginName, realName, status);
    }

    /**
     * 根据id删除用户
     * @param id 用户id
     * */
    public Boolean deleteById(String id){
        CheckUtil.notBlank(id,"用户id为空");

        sysUserMapper.deleteByPrimaryKey(id);
        //删除用户角色关系
        sysUserRoleMapper.deleteByUserId(id);

        return true;
    }

    /**
     * 根据id获取记录
     * @param id 主键
     * */
    public SysUserResponse getById(String id){
        CheckUtil.notBlank(id,"用户id为空");

        SysUserResponse userResponse = new SysUserResponse();
        SysUser user = sysUserMapper.selectByPrimaryKey(id);
        List<SysRole> sysRoleTestList = sysUserRoleMapper.getRolesByUserId(id);

        //获取角色id列表
        if(sysRoleTestList != null&&!sysRoleTestList.isEmpty()){
            List<String> roleIdList = new ArrayList<String>();
            for(SysRole role:sysRoleTestList){
                roleIdList.add(role.getId());
            }
            userResponse.setRoleIdList(roleIdList);
        }

        userResponse.setId(user.getId());
        userResponse.setLoginName(user.getLoginName());
        userResponse.setMobile(user.getMobile());
        userResponse.setOrgId(user.getOrgId());
        userResponse.setOrgName(user.getOrgName());
        userResponse.setRealName(user.getRealName());

        return userResponse;
    }

    /**
     * 全量更新
     * @param userRequest 记录
     * */
    public Boolean updateUser(UserUpdateRequest userRequest){
        CheckUtil.notNull(userRequest,"更新用户为空");

        //登录名不能重复
        SysUser old = sysUserMapper.getByLoginName(userRequest.getLoginName());
        CheckUtil.check(old == null||old.getId().equals(userRequest.getId()),"用户名已存在");

        //更新用户
        SysUser user = sysUserMapper.selectByPrimaryKey(userRequest.getId());
        user.setOrgName(userRequest.getOrgName());
        user.setOrgId(userRequest.getOrgId());
        user.setLoginName(userRequest.getLoginName());
        user.setRealName(userRequest.getRealName());
        user.setMobile(userRequest.getMobile());
        sysUserMapper.updateByPrimaryKey(user);

        //更新用户角色
        addUserRole(user.getId(),userRequest.getRoleIdList());

        return true;
    }

    /**
     * 添加用户
     * @param user 记录
     * @return String 主键id
     * */
    @Transactional(rollbackForClassName = "Exception")
    public String addUser(UserAddRequest user){
        CheckUtil.notNull(user,"添加用户为空");
        CheckUtil.notBlank(user.getLoginName(),"登录名为空");
        CheckUtil.notBlank(user.getOrgId(),"组织机构为空");
        CheckUtil.notBlank(user.getOrgName(),"组织机构为空");

        //登录名不能重复
        SysUser old = sysUserMapper.getByLoginName(user.getLoginName());
        CheckUtil.check(old == null,"该用户名已经存在");

        SysUser newUser = new SysUser();
        newUser.setId(UUIDUtil.getUUID());
        newUser.setLoginName(user.getLoginName());
        newUser.setIsForbidden("0");
        newUser.setPassword(Constants.INIT_PWD);
        newUser.setMobile(user.getMobile());
        newUser.setOrgId(user.getOrgId());
        newUser.setOrgName(user.getOrgName());
        newUser.setRealName(user.getRealName());
        sysUserMapper.insertSelective(newUser);

        log.info("用户新增成功，开始关联用户角色");
        List<String> roleIdList = user.getRoleIdList();
        addUserRole(newUser.getId(),roleIdList);

        return newUser.getId();
    }

    /**
     * 初始化密码
     * @param userId 用户id
     * */
    public String initPwd(String userId){
        String pwd = PasswordUtil.getRandomPwd();
        String sha1Pwd = (new SHA1()).getDigestOfString(pwd);//SHA1加密

        SysUser user = new SysUser();
        user.setId(userId);
        user.setPassword(sha1Pwd);
        sysUserMapper.updateByPrimaryKeySelective(user);

        return pwd;
    }

    /**
     * 更换密码
     * @param  loginName 登录名
     * @param  newPwd 新密码
     * */
    public Boolean changePwd(String loginName,String oldPwd,String newPwd){
        CheckUtil.notBlank(loginName,"登录名为空");
        CheckUtil.notBlank(oldPwd,"旧密码为空");
        CheckUtil.notBlank(newPwd,"新密码为空");

        SysUser user = sysUserMapper.getByLoginName(loginName);
        CheckUtil.notNull(user,"该用户不存在");

        int n = sysUserMapper.countUserByNameAndPwd(loginName,oldPwd);
        CheckUtil.check(n>0,"密码错误");

        user.setPassword(newPwd);
        sysUserMapper.updateByPrimaryKeySelective(user);

        return true;
    }


    /*************************私有方法区***********************************/
    /**
     * 关联用户角色
     * @param id 用户id
     * @param roleIdList 角色id列表
     * */
    private Boolean addUserRole(String id,List<String> roleIdList){
        CheckUtil.notBlank(id,"用户id为空");

        //先批量删除再添加
        sysUserRoleMapper.deleteByUserId(id);

        if(roleIdList == null||roleIdList.isEmpty()){
            return true;
        }

        List<SysUserRole> userRoleTestList = new Stack<SysUserRole>();
        for(String roleId:roleIdList){
        	SysUserRole userRoleTest = new SysUserRole();
            userRoleTest.setId(UUIDUtil.getUUID());
            userRoleTest.setUserId(id);
            userRoleTest.setRoleId(roleId);
            userRoleTestList.add(userRoleTest);
        }

        //批量插入
        sysUserRoleMapper.batchInsert(userRoleTestList);
        return true;
    }
/*	@Override
	public SysUser getByLoginName(SysUserExample example) {
		// TODO Auto-generated method stub
		return null;
	}
*/	

}
