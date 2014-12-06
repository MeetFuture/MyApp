package com.tangqiang;

import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 测试应用
 * 
 * @author tom
 * 
 */
public class DataSendTest {

	public static void main(String[] args) {
		DataSendTest t = new DataSendTest();
		t.sendApply();
	}

	/**
	 * 发送键盘控制信息
	 */
	private void sendKey() {
		SocketAddress address = new InetSocketAddress("localhost", 8095);
		Socket sc = new Socket();
		try {
			sc.connect(address);
			OutputStream os = sc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			while (true) {
				/** 电脑控制 */
				dos.writeByte(0X11);
				/** 键盘模块 */
				dos.writeByte(0X02);
				dos.writeInt(13);
				dos.writeByte(0x01);
				dos.writeInt(39);
				dos.writeLong(System.currentTimeMillis());
				dos.flush();
				Thread.sleep(1000);
				
				/** 电脑控制 */
				dos.writeByte(0X11);
				/** 键盘模块 */
				dos.writeByte(0X02);
				dos.writeInt(13);
				dos.writeByte(0x02);
				dos.writeInt(39);
				dos.writeLong(System.currentTimeMillis());
				dos.flush();
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送应用控制信息
	 */
	private void sendApply() {
		SocketAddress address = new InetSocketAddress("localhost", 8094);
		Socket sc = new Socket();
		try {
			sc.connect(address);
			OutputStream os = sc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			while (true) {
				/** 电脑控制 */
				dos.writeByte(0X11);
				/** 模块 */
				dos.writeByte(0X03);
				dos.writeInt(10);
				dos.writeByte(0x01);
				dos.writeByte(0x04);
				dos.writeLong(System.currentTimeMillis());
				dos.flush();
				Thread.sleep(20000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getKeyCode() {
		javax.swing.JFrame jf = new javax.swing.JFrame();
		jf.setTitle("键盘监听");
		jf.setSize(400, 300);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		jf.addKeyListener(new java.awt.event.KeyListener() {
			public void keyPressed(KeyEvent e) {
				int Keynum = e.getKeyCode();
				System.out.println(Keynum);
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}
		});
	}
}
