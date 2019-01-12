package com.yst.mall.web.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yst.mall.model.SysAccessPermission;
import com.yst.mall.service.SysAccessPermissionService;

/**
 * shiro 动态权限配置相关服务
 *
 * @author gameloft9
 */
@Service
@Slf4j
public class ShiroConfigService {
	private final Logger log = LoggerFactory.getLogger(ShiroConfigService.class);

    @Autowired
    SysAccessPermissionService sysAccessPermissionServiceImpl;

    /**
     * 从数据库加载权限列表
     */
    public Map<String, String> loadFilterChainDefinitions() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<SysAccessPermission> list = sysAccessPermissionServiceImpl.getAll();
        for (SysAccessPermission item : list) {
            filterChainDefinitionMap.put(item.getUrl(), item.getRoles());
        }
        return filterChainDefinitionMap;
    }

    /**
     * 更新权限,解决需要重启tomcat才能生效权限的问题
     */
    public void updatePermission() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            ServletContext servletContext = request.getSession().getServletContext();
            AbstractShiroFilter shiroFilter = (AbstractShiroFilter) WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getBean("myShiroFilter");

            synchronized (shiroFilter) {
                // 获取过滤管理器
                PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
                DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
                // 清空初始权限配置
                manager.getFilterChains().clear();
                // 重新获取资源
                Map<String, String> chains = loadFilterChainDefinitions();

                for (Map.Entry<String, String> entry : chains.entrySet()) {
                    String url = entry.getKey();
                    String chainDefinition = entry.getValue().trim().replace(" ", "");

                    manager.createChain(url, chainDefinition);
                }
                log.info("更新权限成功！！");
            }
        } catch (Exception e) {
            log.error("更新权限到shiro异常", e);
        }
    }
}
