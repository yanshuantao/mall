package com.yst.mall.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.mall.bean.extend.SysMenuExtend;
import com.yst.mall.common.bean.MenuTreeResponse;
import com.yst.mall.common.data.PageRange;
import com.yst.mall.dao.SysMenuMapper;
import com.yst.mall.dao.SysMenuRoleMapper;
import com.yst.mall.dao.SysRoleMapper;
import com.yst.mall.dao.SysUserRoleMapper;
import com.yst.mall.model.SysMenu;
import com.yst.mall.model.SysMenuRole;
import com.yst.mall.model.SysRole;
import com.yst.mall.model.SysUser;
import com.yst.mall.service.SysMenuService;
import com.yst.mall.util.layui.CheckUtil;
import com.yst.mall.util.layui.Constants;
import com.yst.mall.util.layui.UUIDUtil;
import com.yst.mall.util.layui.UserInfoUtil;

/**
 * Created by gameloft9 on 2017/12/6.
 */
@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {
	private final Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    SysMenuRoleMapper sysMenuRoleMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    /*@Autowired
    SysAccessPermissionMapper sysAccessPermissionMapper;

    @Autowired
    ShiroConfigService shiroConfigService;*/

    /**
     * 根据角色获取可见菜单列表
     *
     * @param User 用户
     */
    public List<MenuTreeResponse> getMenuByRoles(SysUser user) {
        //拿到角色id列表
        List<SysRole> roles = sysUserRoleMapper.getRolesByUserId(user.getId());
        List<String> roleIds = new ArrayList<String>();
        for (SysRole role : roles) {
            roleIds.add(role.getId());
        }

        //查询菜单列表
        List<SysMenu> menus = new ArrayList<SysMenu>();
        menus = sysMenuRoleMapper.getDistinctMenusByRoleIds(roleIds);

        return generateMenuTree(menus);
    }

    /**
     * 分页获取菜单列表
     *
     * @param page         页序
     * @param limit        分页大小
     * @param menuName     菜单名称
     * @param menuCode     菜单编码
     * @param parentMenuId 父菜单主键
     */
    public List<SysMenuExtend> getAll(String page, String limit, String menuName, String menuCode, String parentMenuId) {
        PageRange pageRange = new PageRange(page, limit);

        return sysMenuMapper.getAll(pageRange.getStart(), pageRange.getEnd(), menuName, menuCode, parentMenuId);
    }

    /**
     * 获取菜单列表大小
     *
     * @param menuName     菜单名称
     * @param menuCode     菜单编码
     * @param parentMenuId 父菜单主键
     */
    public int countGetAll(String menuName, String menuCode, String parentMenuId) {
        return sysMenuMapper.countGetAll(menuName, menuCode, parentMenuId);
    }

    /**
     * 获取一级菜单列表
     */
    public List<SysMenu> getFirstClassMenus() {
        List<SysMenu> menus = new ArrayList<SysMenu>();
        menus = sysMenuMapper.getFirstClassMenus();
        return menus;
    }

    /**
     * 添加菜单
     *
     * @param menuName     菜单名称
     * @param menuUrl      访问链接
     * @param menuType     菜单类型
     * @param parentMenuId 父菜单id
     * @return String 菜单id
     */
    public String addMenu(String menuName, String menuUrl, String menuType, String parentMenuId, String requestUrl, String sort) {
        CheckUtil.notBlank(menuName, "菜单名称为空");
        CheckUtil.notBlank(menuUrl, "菜单访问链接为空");
        CheckUtil.notBlank(menuType, "菜单类型为空");
        CheckUtil.notBlank(requestUrl, "后台请求路径为空");
        CheckUtil.notBlank(sort, "排序号为空");

        //菜单名称不能重复
        SysMenu dataBaseMenu = sysMenuMapper.getByMenuName(menuName);
        CheckUtil.check(dataBaseMenu == null, "该菜单名称已经存在");

        //后台请求路径不能重复
        dataBaseMenu = sysMenuMapper.getByRequestUrl(requestUrl);
        CheckUtil.check(dataBaseMenu == null, "后台请求路径已经存在");

        //生成菜单code
        String code = generateMenuCode(menuType, parentMenuId);

        SysMenu menu = new SysMenu();
        menu.setId(UUIDUtil.getUUID());
        menu.setCode(code);
        menu.setCreateUser(UserInfoUtil.getUser().toString());
        menu.setHref(menuUrl);
        menu.setRequestUrl(requestUrl);
        menu.setIcon(Constants.MenuIcon.WEN_BEN.value);
        if (StringUtils.isNotBlank(parentMenuId)) {
            menu.setParentId(parentMenuId);
        }
        menu.setTitle(menuName);
        menu.setSort(Integer.parseInt(sort));

        sysMenuMapper.insertSelective(menu);
        return menu.getId();
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     */
    @Transactional(rollbackForClassName = "Exception")
    public Boolean deleteMenu(String menuId) {
    	/*
        CheckUtil.notBlank(menuId, "菜单id为空");

        SysMenu menu = sysMenuMapper.selectByPrimaryKey(menuId);
        if (StringUtils.isBlank(menu.getParentId())) {//删除的是一级菜单
            log.info("删除菜单类型:一级菜单");
            int count = sysMenuMapper.getChildrenCount(menuId);
            CheckUtil.check(count <= 0, "含有子菜单，不能删除");
        }

        //删除菜单
        sysMenuMapper.deleteByPrimaryKey(menuId);
        //删除菜单角色表中对应记录
        sysMenuRoleMapper.deleteByMenuId(menuId);
        //删除访问权限表对应记录
        SysAccessPermission sysAccessPermission = sysAccessPermissionMapper.selectByUrl(menu.getRequestUrl());
        if (sysAccessPermission != null) {
            sysAccessPermission.setIsDeleted("1");
            sysAccessPermissionMapper.updateByPrimaryKeySelective(sysAccessPermission);
        }
*/
        return true;
    }

    /**
     * 根据主键获取菜单信息
     *
     * @param id 菜单主键
     */
    public SysMenuExtend getById(String id) {
        SysMenuExtend menu = sysMenuMapper.getById(id);
        if (menu != null) {//获取所属角色id列表
            List<String> idList = sysMenuRoleMapper.getRoleIdsByMenuId(id);
            menu.setRoleIdList(idList);
        }

        return menu;
    }

    /**
     * 更新菜单
     *
     * @param menuName   菜单名称
     * @param menuUrl    链接
     * @param requestUrl 后台请求路径
     * @param sort       排序号
     * @param idList     角色列表
     */
    public Boolean updateMenu(String menuId, String menuName, String menuUrl, String requestUrl, String sort, List<String> idList) {
        /*CheckUtil.notBlank(menuName, "菜单名称为空");
        CheckUtil.notBlank(menuUrl, "访问链接为空");

        //菜单名称不能重复
        SysMenu menu = sysMenuMapper.getByMenuName(menuName);
        CheckUtil.check(menu == null || (menu.getId().equals(menuId)), "该菜单名称已经存在");

        //更新菜单
        SysMenu newMenu = new SysMenu();
        newMenu.setId(menuId);
        newMenu.setTitle(menuName);
        newMenu.setHref(menuUrl);
        newMenu.setRequestUrl(requestUrl);
        newMenu.setSort(new Short(sort));
        sysMenuMapper.updateByPrimaryKeySelective(newMenu);

        //更新菜单角色设置
        updateMenuRole(menuId, idList);

        //更新访问权限
        updateAccessPrivilege(requestUrl, idList);*/

        return true;
    }

/**************************私有方法区*****************************************/
    /**
     * 根据菜单列表生成菜单树
     *
     * @param menus 菜单列表
     */
    private List<MenuTreeResponse> generateMenuTree(List<SysMenu> menus) {
        List<MenuTreeResponse> menuTreeList = new LinkedList<MenuTreeResponse>();
        //将菜单转换为特定格式
        if (!menus.isEmpty()) {
            //先找到根菜单
            Iterator<SysMenu> it = menus.iterator();
            while (it.hasNext()) {
                SysMenu menu = it.next();
                if (StringUtils.isBlank(menu.getParentId())) {
                    MenuTreeResponse menuTree = new MenuTreeResponse();
                    menuTree.setId(menu.getId());
                    menuTree.setTitle(menu.getTitle());
                    menuTree.setHref(menu.getHref());
                    menuTree.setIcon(menu.getIcon());
                    menuTree.setTarget(menu.getTarget());
                    menuTree.setSpread(false);
                    menuTreeList.add(menuTree);
                    it.remove();//删除根菜单
                }
            }

            //填充子菜单（只支持两级菜单）
            for (SysMenu menu : menus) {
                String parentId = menu.getParentId();
                for (MenuTreeResponse menuTree : menuTreeList) {
                    if (menuTree.getId().equals(parentId)) {
                        MenuTreeResponse child = new MenuTreeResponse();
                        child.setId(menu.getId());
                        child.setTitle(menu.getTitle());
                        child.setHref(menu.getHref());
                        child.setIcon(menu.getIcon());
                        child.setTarget(menu.getTarget());
                        child.setSpread(false);
                        menuTree.getChildren().add(child);
                    }
                }
            }
        }

        return menuTreeList;
    }

    /**
     * 生成菜单编码;
     * 一级菜单是最大一级菜单code+1,二级菜单是父菜单code+'-'+该菜单下二级菜单最大code+1
     *
     * @param menuType 菜单类型 1-一级菜单，2-二级菜单
     */
    private String generateMenuCode(String menuType, String parentMenuId) {
        String maxCode = "";

        if (Constants.MenuType.FIRST_CLASS.value.equals(menuType)) {//一级菜单
            //找到一级菜单中的最大编码
            maxCode = sysMenuMapper.getMaxCodeOfFirstClass();
            if (StringUtils.isBlank(maxCode)) {
                return Constants.INIT_FIRST_CLASS_MENU_CODE;
            }
            Integer code = Integer.parseInt(maxCode) + 1;
            return code.toString();
        } else {
            //找到二级菜单中的最大编码
            maxCode = sysMenuMapper.getMaxCodeOfSecondClass(parentMenuId);
            SysMenu parentMenu = sysMenuMapper.selectByPrimaryKey(parentMenuId);
            if (StringUtils.isBlank(maxCode)) {
                maxCode = parentMenu.getCode() + "-" + Constants.INIT_SECOND_CLASS_MENU_CODE;
                return maxCode;
            }

            Integer code = Integer.parseInt(maxCode.split("-")[1]) + 1;
            return parentMenu.getCode() + "-" + code.toString();
        }

    }

    /**
     * 更新菜单角色及权限
     */
    /*private void updateMenuRole(String menuId, List<String> idList) {
        // 先清空原来的
        clearMenuRole(menuId);

        //新增或者更新菜单角色表
        for (String roleId : idList) {
            SysMenuRole menuRole = new SysMenuRole();
            menuRole.setMenuId(menuId);
            menuRole.setRoleId(roleId);
            //有则更新，没有则新增
            insertOrUpdateMenuRole(menuRole);
        }
    }*/

    /**
     * 新增或更新菜单角色
     *
     * @param sysMenuRole 菜单角色对象
     */
    private int insertOrUpdateMenuRole(SysMenuRole sysMenuRole) {
        CheckUtil.notNull(sysMenuRole, "菜单角色对象为空");
        CheckUtil.notBlank(sysMenuRole.getMenuId(), "菜单id为空");
        CheckUtil.notBlank(sysMenuRole.getRoleId(), "角色id为空");

        SysMenuRole oldMenu = sysMenuRoleMapper.selectByMenuIdAndRoleId(sysMenuRole.getMenuId(), sysMenuRole.getRoleId());
        if (null == oldMenu) {
            log.info("开始新增菜单角色关联记录");
            sysMenuRole.setId(UUIDUtil.getUUID());
            return sysMenuRoleMapper.insertSelective(sysMenuRole);
        }

        log.info("开始更新菜单角色关联记录");
        sysMenuRole.setId(oldMenu.getId());
        return sysMenuRoleMapper.updateByPrimaryKeySelective(sysMenuRole);
    }

    /**
     * 更新访问权限
     *
     * @param requestUrl 后台请求路径
     * @param roleIdList 角色id列表
     */
    /*private void updateAccessPrivilege(String requestUrl, List<String> roleIdList) {
        CheckUtil.notBlank(requestUrl, "后台访问链接为空");
        //CheckUtil.notNull(roleIdList, "角色id列表为空");
        //CheckUtil.check(!roleIdList.isEmpty(), "角色id列表为空");

        // 先清空原来的
        sysAccessPermissionMapper.deleteByRequestUrl(requestUrl);
        if (!(roleIdList == null || roleIdList.isEmpty())) {
            //获取角色名称
            List<String> roleNames = sysRoleMapper.getRoleNamesByIds(roleIdList);
            //生成权限
            String privilege = generateAccessPrivilege(roleNames);

            //更新
            SysAccessPermission sysAccessPermission = sysAccessPermissionMapper.selectByUrl(requestUrl);
            if (sysAccessPermission != null) {//更新
                sysAccessPermission.setRoles(privilege);
                sysAccessPermissionMapper.updateByPrimaryKeySelective(sysAccessPermission);
                return;
            }

            //新增
            sysAccessPermission = new SysAccessPermission();
            sysAccessPermission.setId(UUIDUtil.getUUID());
            sysAccessPermission.setUrl(requestUrl);
            sysAccessPermission.setRoles(privilege);
            sysAccessPermission.setCreateUser(UserInfoUtil.getUser().toString());
            sysAccessPermission.setSort(ShiroFilterSortUtil.getRandomAuthorSort());

            sysAccessPermissionMapper.insertSelective(sysAccessPermission);
        }

        //重新加载shiro filter
        shiroConfigService.updatePermission();
    }*/

    /**
     * 生成访问权限
     *
     * @param roleNames 角色列表
     */
    /*private String generateAccessPrivilege(List<String> roleNames) {
        //CheckUtil.notNull(roleNames, "角色列表为null");
        //CheckUtil.check(!roleNames.isEmpty(), "角色列表为空");
        String finalPrivilege;
        String privilege_authc = "authc,";

        if (roleNames == null || roleNames.isEmpty()) {
            return "authc";
        } else if (roleNames.size() == 1) {
            String privilege_role = "roles[";//角色等于一个为"authc,roles[admin]"格式
            privilege_role = privilege_role + roleNames.get(0) + "]";
            finalPrivilege = privilege_authc + privilege_role;

            return finalPrivilege;
        } else {
            //生成权限
            String privilege_role = "roleOr[";

            for (int i = 0; i < roleNames.size() - 1; i++) {
                privilege_role += roleNames.get(i);
                privilege_role += ",";
            }

            privilege_role += roleNames.get(roleNames.size() - 1);
            privilege_role += "]";
            finalPrivilege = privilege_authc + privilege_role;

            return finalPrivilege;
        }
    }*/

    /**
     * 清空菜单角色关联关系
     *
     * @param menuId
     */
   /* private void clearMenuRole(String menuId) {
        sysMenuRoleMapper.deleteByMenuId(menuId);
    }*/
}
