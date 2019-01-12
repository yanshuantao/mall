package com.yst.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.mall.common.data.ResultData;
import com.yst.mall.common.response.IResult;
import com.yst.mall.common.response.ResultBean;
import com.yst.mall.model.SysUser;
import com.yst.mall.service.SysUserService;

@Controller
public class LoginController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<SysUser> login(@RequestParam(value="name") String userName,
			@RequestParam(value="pwd") String pwd,HttpServletRequest request){
		ResultData<SysUser> result =new ResultData<SysUser>();
		//当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //获取基于用户名和密码的令牌(生产环境下密码需要转换为加密后的密码)
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
        try {
            //提交认证，会调用Realm的doGetAuthenticationInfo，进行认证
            currentUser.login(token);
            result.setCode(ResultData.SUCCESS);
            SysUser user=(SysUser) SecurityUtils.getSubject().getPrincipal();
            result.setData(user);
        } catch(UnknownAccountException e){
        	result.setCode(ResultData.BIZ_FAIL);
        	result.setMsg("用户不存在");
//            log.info("用户不存在");
//            throw new BizException(AbstractResult.BIZ_FAIL,"用户不存在");
        }catch(IncorrectCredentialsException e){
//            log.info("用户名或密码错误");
//            throw new BizException(AbstractResult.BIZ_FAIL,"用户名或密码错误");
        	result.setCode(ResultData.BIZ_FAIL);
        	result.setMsg("用户名或密码错误");

        }catch (AuthenticationException e) {
//            log.error("认证异常",e);
//            throw new BizException(AbstractResult.BIZ_FAIL,"认证异常");
        	result.setCode(ResultData.BIZ_FAIL);
        	result.setMsg("认证异常");
        }
        if(currentUser.isAuthenticated()){
        	request.getSession().setAttribute("sysUser", userName);
        }
		return result;
	}
	 /**
     * 登出
     * */
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    @ResponseBody
    public IResult logout(HttpServletRequest request){
    	request.getSession().removeAttribute("sysUser");
    	ResultBean result=new ResultBean();
        return result;
    }

    /**
     * 未授权
     * */
    @RequestMapping(value = "/401", method = RequestMethod.GET)
    @ResponseBody
    public IResult unauthorization(HttpServletResponse response){
        //前端通过http状态判断
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new ResultBean<String>();
    }

    /**
     * 未登录
     * */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    @ResponseBody
    public IResult needLogin(HttpServletResponse response){
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return new ResultBean<String>();
    }
}
