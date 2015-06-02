/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foal.question.jersey.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.foal.question.jersey.command.common.GetVersionCommand;

/**
 * 处理器工厂类
 * @author jackyli515
 */
public class CommandRouter {
    private static final Logger logger = Logger.getLogger(CommandRouter.class);
    private static Map<Short, ICommand> handlerServiceMap = new HashMap<Short, ICommand>();
    /**
     * 获取处理接口
     * @param command
     * @return 
     */
    public static ICommand getHandlerService(Short command){
        if(handlerServiceMap.containsKey(command)){
            return handlerServiceMap.get(command);
        }else{
        	logger.error("service handler not found : command="+command);
        }
        return null;
    }
    /**
     * 注册处理服务
     * @param command
     * @param handlerService 
     */
    public static void register(Short command,ICommand handlerService){
        if(handlerServiceMap.containsKey(command)){
            logger.error("处理句柄已存在:"+command+"");
            throw new IllegalStateException("HandlerService注册失败：handlerId有重复！"+command);
        }
        handlerServiceMap.put(command, handlerService);
        
    }
    public static void clear(){
        handlerServiceMap.clear();
    }
    
    /**
     * 初始化服务接口注册 
     */
    public static void initHandlerService(){
    	//服务接口注册
        clear();
        
        register(ICommand.GetVersion, new GetVersionCommand());
    }
}
