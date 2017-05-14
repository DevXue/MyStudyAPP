package xue.myapp.module.main.IM;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 作者： 薛
 * 创建时间:2017/5/14
 * 功能描述：
 */

public class NettyClient  {


    public void runConnec() throws Exception{
        EventLoopGroup group=new NioEventLoopGroup();
        try {
            Bootstrap b=new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                                sc.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture channelFuture=b.connect("192.168.1.199",8979).sync();

            channelFuture.channel().write(Unpooled.copiedBuffer("hello !".getBytes()));
            channelFuture.channel().flush();
            channelFuture.channel().closeFuture().sync();
            group.shutdownGracefully();

        }finally {
            group.shutdownGracefully();
        }
    }
}
