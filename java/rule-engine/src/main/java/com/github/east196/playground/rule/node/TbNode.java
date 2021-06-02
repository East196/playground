package com.github.east196.playground.rule.node;


import com.github.east196.playground.rule.TbContext;
import com.github.east196.playground.rule.engine.TbMsg;

import java.util.concurrent.ExecutionException;


public interface TbNode {

    void init(TbContext ctx, TbNodeConfiguration configuration) throws TbNodeException;

    void onMsg(TbContext ctx, TbMsg msg) throws ExecutionException, InterruptedException, TbNodeException;

    void destroy();

    default void onPartitionChangeMsg(TbContext ctx, PartitionChangeMsg msg) {}

}