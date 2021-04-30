
package com.github.east196.playground.rule.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.east196.playground.entity.EntityId;
import com.github.east196.playground.rule.chain.RuleChainId;
import com.github.east196.playground.rule.node.RuleNodeId;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ashvayka on 13.01.18.
 */
@Data
@Builder
@Slf4j
public final class TbMsg implements Serializable {

    private final String queueName;
    private final UUID id;
    private final long ts;
    private final String type;
    private final EntityId originator;
    private final TbMsgMetaData metaData;
    private final TbMsgDataType dataType;
    private final String data;
    private final RuleChainId ruleChainId;
    private final RuleNodeId ruleNodeId;
    //This field is not serialized because we use queues and there is no need to do it
    @JsonIgnore
    transient private final TbMsgCallback callback;

    public static TbMsg newMsg(String queueName, String type, EntityId originator, TbMsgMetaData metaData, String data, RuleChainId ruleChainId, RuleNodeId ruleNodeId) {
        return new TbMsg(queueName, UUID.randomUUID(), System.currentTimeMillis(), type, originator, metaData.copy(), TbMsgDataType.JSON, data, ruleChainId, ruleNodeId, TbMsgCallback.EMPTY);
    }

    public static TbMsg newMsg(String type, EntityId originator, TbMsgMetaData metaData, String data) {
        return new TbMsg(ServiceQueue.MAIN, UUID.randomUUID(), System.currentTimeMillis(), type, originator, metaData.copy(), TbMsgDataType.JSON, data, null, null, TbMsgCallback.EMPTY);
    }

    public static TbMsg newMsg(String queueName, String type, EntityId originator, TbMsgMetaData metaData, String data) {
        return new TbMsg(queueName, UUID.randomUUID(), System.currentTimeMillis(), type, originator, metaData.copy(), TbMsgDataType.JSON, data, null, null, TbMsgCallback.EMPTY);
    }

    public static TbMsg newMsg(String type, EntityId originator, TbMsgMetaData metaData, TbMsgDataType dataType, String data) {
        return new TbMsg(ServiceQueue.MAIN, UUID.randomUUID(), System.currentTimeMillis(), type, originator, metaData.copy(), dataType, data, null, null, TbMsgCallback.EMPTY);
    }

    public static TbMsg newMsg(String type, EntityId originator, TbMsgMetaData metaData, TbMsgDataType dataType, String data, RuleChainId ruleChainId, RuleNodeId ruleNodeId) {
        return new TbMsg(ServiceQueue.MAIN, UUID.randomUUID(), System.currentTimeMillis(), type, originator, metaData.copy(), dataType, data, ruleChainId, ruleNodeId, TbMsgCallback.EMPTY);
    }

    public static TbMsg newMsg(String type, EntityId originator, TbMsgMetaData metaData, String data, TbMsgCallback callback) {
        return new TbMsg(ServiceQueue.MAIN, UUID.randomUUID(), System.currentTimeMillis(), type, originator, metaData.copy(), TbMsgDataType.JSON, data, null, null, callback);
    }

    public static TbMsg transformMsg(TbMsg origMsg, String type, EntityId originator, TbMsgMetaData metaData, String data) {
        return new TbMsg(origMsg.getQueueName(), origMsg.getId(), origMsg.getTs(), type, originator, metaData.copy(), origMsg.getDataType(),
                data, origMsg.getRuleChainId(), origMsg.getRuleNodeId(), origMsg.getCallback());
    }

    public static TbMsg newMsg(TbMsg tbMsg, RuleChainId ruleChainId, RuleNodeId ruleNodeId) {
        return new TbMsg(tbMsg.getQueueName(), UUID.randomUUID(), tbMsg.getTs(), tbMsg.getType(), tbMsg.getOriginator(), tbMsg.getMetaData().copy(),
                tbMsg.getDataType(), tbMsg.getData(), ruleChainId, ruleNodeId, TbMsgCallback.EMPTY);
    }

    private TbMsg(String queueName, UUID id, long ts, String type, EntityId originator, TbMsgMetaData metaData, TbMsgDataType dataType, String data,
                  RuleChainId ruleChainId, RuleNodeId ruleNodeId, TbMsgCallback callback) {
        this.id = id;
        this.queueName = queueName;
        if (ts > 0) {
            this.ts = ts;
        } else {
            this.ts = System.currentTimeMillis();
        }
        this.type = type;
        this.originator = originator;
        this.metaData = metaData;
        this.dataType = dataType;
        this.data = data;
        this.ruleChainId = ruleChainId;
        this.ruleNodeId = ruleNodeId;
        if (callback != null) {
            this.callback = callback;
        } else {
            this.callback = TbMsgCallback.EMPTY;
        }
    }

    public TbMsg copyWithRuleChainId(RuleChainId ruleChainId) {
        return new TbMsg(this.queueName, this.id, this.ts, this.type, this.originator, this.metaData, this.dataType, this.data, ruleChainId, null, callback);
    }

    public TbMsg copyWithRuleNodeId(RuleChainId ruleChainId, RuleNodeId ruleNodeId) {
        return new TbMsg(this.queueName, this.id, this.ts, this.type, this.originator, this.metaData, this.dataType, this.data, ruleChainId, ruleNodeId, callback);
    }

    public TbMsgCallback getCallback() {
        //May be null in case of deserialization;
        if (callback != null) {
            return callback;
        } else {
            return TbMsgCallback.EMPTY;
        }
    }

    public String getQueueName() {
        return queueName != null ? queueName : ServiceQueue.MAIN;
    }
}
