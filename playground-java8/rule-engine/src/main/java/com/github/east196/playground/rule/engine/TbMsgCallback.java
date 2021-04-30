package com.github.east196.playground.rule.engine;

import com.github.east196.playground.rule.node.RuleNodeInfo;

public interface TbMsgCallback {

    TbMsgCallback EMPTY = new TbMsgCallback() {

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailure(RuleEngineException e) {

        }
    };

    void onSuccess();

    void onFailure(RuleEngineException e);

    default void visit(RuleNodeInfo ruleNodeInfo) {
    }

}