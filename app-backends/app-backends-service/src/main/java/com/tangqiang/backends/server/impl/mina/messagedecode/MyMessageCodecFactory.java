package com.tangqiang.backends.server.impl.mina.messagedecode;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;

/**
 * 编解码器工厂
 *
 * @author tom
 * @since 2013-8-2 下午7:47:06
 * @project app-backends-service
 * @package com.tangqiang.backends.server.impl.mina.messagedecode
 */
public class MyMessageCodecFactory extends DemuxingProtocolCodecFactory {
	private Logger logger = Logger.getLogger(MyMessageCodecFactory.class);
	

	/**
	 *  注册编解码器
	 * @param decoder
	 * @param encoder
	 */
	public MyMessageCodecFactory(MessageDecoder decoder,
			MessageEncoder<AbstrMessage> encoder) {
		logger.info("开始注册信息解码器:"+decoder);
		addMessageDecoder(decoder);
		logger.info("开始注册信息编码器 :"+encoder);
		addMessageEncoder(AbstrMessage.class, encoder);
	}
}