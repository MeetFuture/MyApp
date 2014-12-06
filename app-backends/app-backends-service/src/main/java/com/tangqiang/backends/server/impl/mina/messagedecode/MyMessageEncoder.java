package com.tangqiang.backends.server.impl.mina.messagedecode;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;
import com.tangqiang.backends.server.impl.mina.message.MessageKeyBoardRequest;
import com.tangqiang.backends.server.impl.mina.message.MessageMouseRequest;

/**
 * 信息编码并发送 在执行 session。write(messsage) 时调用
 * 
 * @author 汤强
 * @since 2013-8-3 上午12:37:40
 * @project app-backends-service
 * @package com.tangqiang.backends.server.impl.mina.messagedecode
 */
public class MyMessageEncoder implements MessageEncoder<AbstrMessage> {

	private Charset charset;

	public void encode(IoSession session, AbstrMessage message, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = null;
		if (message instanceof MessageMouseRequest) {
			// 需要传输的对象
			AbstrMessage req1 = (MessageMouseRequest) message;
			buf = req1.getEncode(charset);
		} else if (message instanceof MessageKeyBoardRequest) {
			AbstrMessage req2 = (MessageKeyBoardRequest) message;
			buf = req2.getEncode(charset);
		}
		if (null == buf) {
			throw new Exception("信息发送失败! 未知的请求信息!");
		}
		out.write(buf);
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

}
