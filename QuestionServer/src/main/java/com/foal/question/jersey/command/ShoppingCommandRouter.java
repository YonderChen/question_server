/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foal.question.jersey.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.foal.question.jersey.command.shopping.LoadGoodsListCommand;

/**
 * 处理器工厂类
 * @author jackyli515
 */
public class ShoppingCommandRouter {
    private static final Logger logger = Logger.getLogger(ShoppingCommandRouter.class);
    private static Map<Short, IShoppingCommand> handlerServiceMap = new HashMap<Short, IShoppingCommand>();
    /**
     * 获取处理接口
     * @param command
     * @return 
     */
    public static IShoppingCommand getHandlerService(short command){
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
    public static void register(short command,IShoppingCommand handlerService){
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
    	register(IShoppingCommand.LoadGoodsListCommand, new LoadGoodsListCommand());
    }
}
