/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foal.question.jersey.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.foal.question.jersey.command.comment.AddCommentCommand;
import com.foal.question.jersey.command.comment.DelCommentCommand;
import com.foal.question.jersey.command.comment.LoadCommentByCareUserCommand;
import com.foal.question.jersey.command.comment.LoadCommentByOwnerCommand;
import com.foal.question.jersey.command.comment.LoadCommentCommand;
import com.foal.question.jersey.command.comment.ReadAllCommentCommand;
import com.foal.question.jersey.command.comment.ReadCommentCommand;
import com.foal.question.jersey.command.common.EditPasswordCommand;
import com.foal.question.jersey.command.common.EditUserInfoCommand;
import com.foal.question.jersey.command.common.FeedBackCommand;
import com.foal.question.jersey.command.common.GetUserBaseInfoByUidsCommand;
import com.foal.question.jersey.command.common.GetUserinfoCommand;
import com.foal.question.jersey.command.common.GetVersionCommand;
import com.foal.question.jersey.command.common.LoginCommand;
import com.foal.question.jersey.command.common.LoginLocalCommand;
import com.foal.question.jersey.command.common.RegistCommand;
import com.foal.question.jersey.command.common.TipOffCommand;
import com.foal.question.jersey.command.follow.AddFollowCommand;
import com.foal.question.jersey.command.follow.DelFollowCommand;
import com.foal.question.jersey.command.follow.ListTargetFollowCommand;
import com.foal.question.jersey.command.follow.ListTargetFollowersCommand;
import com.foal.question.jersey.command.follow.LoadMyNewFollowersCommand;
import com.foal.question.jersey.command.textimage.TextImageAddCommand;
import com.foal.question.jersey.command.textimage.TextImageByRecordIdCommand;
import com.foal.question.jersey.command.textimage.TextImageDelCommand;
import com.foal.question.jersey.command.textimage.TextImageLoadByFollowCommand;
import com.foal.question.jersey.command.textimage.TextImageLoadMyCommand;
import com.foal.question.jersey.command.textimage.TextImageLoadOthersCommand;
import com.foal.question.jersey.command.textimage.TextImageLoadPraiseUserCommand;
import com.foal.question.jersey.command.textimage.TextImageLoadPublicCommand;
import com.foal.question.jersey.command.textimage.TextImagePraiseCommand;
import com.foal.question.jersey.command.textimage.TextImageShareCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceAddCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceByRecordIdCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceDelCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceLoadByFollowCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceLoadMyCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceLoadOthersCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceLoadPraiseUserCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceLoadPublicCommand;
import com.foal.question.jersey.command.textvoice.TextVoicePraiseCommand;
import com.foal.question.jersey.command.textvoice.TextVoiceShareCommand;

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
    	
    	register(ICommand.Login, new LoginCommand());
    	register(ICommand.Regist, new RegistCommand());
    	register(ICommand.LoginLocal, new LoginLocalCommand());
    	register(ICommand.EditPassword, new EditPasswordCommand());
    	register(ICommand.EditUserInfo, new EditUserInfoCommand());
    	register(ICommand.TipOff, new TipOffCommand());
    	register(ICommand.FeedBack, new FeedBackCommand());
    	register(ICommand.GetUserinfo, new GetUserinfoCommand());
    	register(ICommand.GetUserBaseInfoByUids, new GetUserBaseInfoByUidsCommand());
    	
    	register(ICommand.AddFollow, new AddFollowCommand());
    	register(ICommand.DelFollow, new DelFollowCommand());
    	register(ICommand.ListTargetFollow, new ListTargetFollowCommand());
    	register(ICommand.ListTargetFollowers, new ListTargetFollowersCommand());
    	register(ICommand.LoadMyNewFollowers, new LoadMyNewFollowersCommand());
    	
    	register(ICommand.AddComment, new AddCommentCommand());
    	register(ICommand.DelComment, new DelCommentCommand());
    	register(ICommand.LoadComment, new LoadCommentCommand());
    	register(ICommand.LoadCommentByCareUser, new LoadCommentByCareUserCommand());
    	register(ICommand.ReadComment, new ReadCommentCommand());
    	register(ICommand.LoadCommentByOwner, new LoadCommentByOwnerCommand());
    	register(ICommand.ReadAllComment, new ReadAllCommentCommand());
    	
    	register(ICommand.TextImageAdd, new TextImageAddCommand());
    	register(ICommand.TextImageLoadMy, new TextImageLoadMyCommand());
    	register(ICommand.TextImageLoadOthers, new TextImageLoadOthersCommand());
    	register(ICommand.TextImageLoadPublic, new TextImageLoadPublicCommand());
    	register(ICommand.TextImagePraise, new TextImagePraiseCommand());
    	register(ICommand.TextImageDel, new TextImageDelCommand());
    	register(ICommand.TextImageShare, new TextImageShareCommand());
    	register(ICommand.TextImageLoadPraiseUser, new TextImageLoadPraiseUserCommand());
    	register(ICommand.TextImageLoadByFollow, new TextImageLoadByFollowCommand());
    	register(ICommand.TextImageByRecordId, new TextImageByRecordIdCommand());
    	
    	register(ICommand.TextVoiceAdd, new TextVoiceAddCommand());
    	register(ICommand.TextVoiceLoadMy, new TextVoiceLoadMyCommand());
    	register(ICommand.TextVoiceLoadOthers, new TextVoiceLoadOthersCommand());
    	register(ICommand.TextVoiceLoadPublic, new TextVoiceLoadPublicCommand());
    	register(ICommand.TextVoicePraise, new TextVoicePraiseCommand());
    	register(ICommand.TextVoiceDel, new TextVoiceDelCommand());
    	register(ICommand.TextVoiceShare, new TextVoiceShareCommand());
    	register(ICommand.TextVoiceLoadPraiseUser, new TextVoiceLoadPraiseUserCommand());
    	register(ICommand.TextVoiceLoadByFollow, new TextVoiceLoadByFollowCommand());
    	register(ICommand.TextVoiceByRecordId, new TextVoiceByRecordIdCommand());
    }
}
