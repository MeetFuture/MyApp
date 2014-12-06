package com.tangqiang.backends.server.impl.mina.messagedecode;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;
import com.tangqiang.backends.server.impl.mina.message.MessageApplyRequest;
import com.tangqiang.backends.server.impl.mina.message.MessageComputerControl;
import com.tangqiang.backends.server.impl.mina.message.MessageKeyBoardRequest;
import com.tangqiang.backends.server.impl.mina.message.MessageMouseRequest;

/**
 * mina 服务器 信息解码器  
 * 
 * @author 汤强
 * 
 */
public class MyMessageDecoder implements MessageDecoder {
	private Logger logger = Logger.getLogger(MyMessageDecoder.class);

	private Charset charset;

	/**
	 *  检查给定的IoBuffer是否适合解码
	 */
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		// 报头长度==6      2个byte + 一个 int
		logger.debug("开始判断信息是否能够被解码:"+in);
		if (in.remaining() < 6) {
			return MessageDecoderResult.NEED_DATA;
		}
		// tag正常
		byte bMsgType = in.get();
		byte bMoudle = in.get();
		if (bMsgType == AbstrMessage.COMPUTER_CONNTROL) {
			logger.debug("可以被解码的信息,信息标识符为:"+bMsgType+"-"+bMoudle);
		} else {
			logger.error("未知的请求类型....");
			return MessageDecoderResult.NOT_OK;
		}
		// 真实数据长度
		int len = in.getInt();
		if (in.remaining() < len) {
			return MessageDecoderResult.NEED_DATA;
		}
		return MessageDecoderResult.OK;
	}

	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		logger.debug("开始解码:" + in.toString());
		CharsetDecoder decoder = charset.newDecoder();
		AbstrMessage message = null;
		byte bMsgType = in.get(); //信息类型
		byte bModuleType = in.get();//模块类型
		int len = in.getInt(); // 信息长度
		byte[] temp = new byte[len];
		in.get(temp); // 数据区
		// ===============解析数据做准备======================
		IoBuffer buf = IoBuffer.allocate(50).setAutoExpand(true);
		buf.put(temp);
		buf.flip(); // 获取数据区
		
		
		// ================开始解码=========================
		// 注意先把16进制标识值转换为short类型的十进制数据，然后与tag比较
		if (bMsgType == AbstrMessage.COMPUTER_CONNTROL) {
			if ( bModuleType == MessageComputerControl.MOUSE_REQUEST) { // 服务端解码
				// logger.info("进入服务端解码过程!");
				message = new MessageMouseRequest();
				message.getDecode(buf, decoder);
			}else if( bModuleType == MessageComputerControl.KEY_REQUEST){
				message = new MessageKeyBoardRequest();
				message.getDecode(buf, decoder);
			}else if (bModuleType == MessageComputerControl.APP_REQUEST){
				message = new MessageApplyRequest();
				message.getDecode(buf, decoder);
			}
		} else  {
			throw new Exception("未找到解码器....");
		}
		
		out.write(message);

		logger.debug("解码成功:"+message.toString());
		// ================解码成功=========================
		return MessageDecoderResult.OK;
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
	}
	
	
	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}
}