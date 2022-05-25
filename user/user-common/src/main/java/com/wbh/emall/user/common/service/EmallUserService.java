package com.wbh.emall.user.common.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbh.emall.user.common.entity.EmallUser;
import com.wbh.emall.user.common.entity.UserInfo;
import com.wbh.emall.user.common.mapper.EmallUserMapper;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author WBH
 * @since 2022/2/14
 */
@Slf4j
@Service
public class EmallUserService extends ServiceImpl<EmallUserMapper, EmallUser> {
    
    private final WxMaService maService;
    private final EmallUserMapper userMapper;
    
    public EmallUserService(WxMaService maService, EmallUserMapper userMapper) {
        this.maService = maService;
        this.userMapper = userMapper;
    }
    
    /**
     * 根据用户的微信小程序的code获取用户信息
     *
     * @param userInfo 用户基本信息
     * @return 用户信息
     * @throws WxErrorException 微信小程序异常
     */
    public EmallUser getUser(UserInfo userInfo) throws WxErrorException {
        // 获取用户基本信息
        WxMaJscode2SessionResult session = maService.getUserService()
                .getSessionInfo(userInfo.getCode());
        EmallUser emallUser = userMapper.selectByOpenId(session.getOpenid());
        // 用户不存在，则新建用户
        if (Objects.isNull(emallUser)) {
            emallUser = new EmallUser();
            BeanUtils.copyProperties(session, emallUser);
            BeanUtils.copyProperties(userInfo, emallUser);
            int uid = userMapper.insert(emallUser);
        }
        return emallUser;
    }
    
    /**
     * 更新用户数据
     *
     * @param user 需要更新的用户数据
     * @return 更新的数据数目
     */
    public int updateUser(EmallUser user) {
        return userMapper.updateById(user);
    }
    
}
