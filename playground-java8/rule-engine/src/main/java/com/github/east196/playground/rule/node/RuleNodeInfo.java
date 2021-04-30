package com.github.east196.playground.rule.node;

public class RuleNodeInfo {
    private final String label;

    public RuleNodeInfo(RuleNodeId id, String ruleChainName, String ruleNodeName) {
        this.label = "[RuleChain: " + ruleChainName + "|RuleNode: " + ruleNodeName + "(" + id + ")]";
    }

    @Override
    public String toString() {
        return label;
    }
}