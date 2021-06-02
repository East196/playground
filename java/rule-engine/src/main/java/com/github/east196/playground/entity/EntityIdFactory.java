package com.github.east196.playground.entity;

import java.util.UUID;

public class EntityIdFactory {

    public static EntityId getByTypeAndId(String type, String uuid) {
        return getByTypeAndUuid(EntityType.valueOf(type), UUID.fromString(uuid));
    }

    public static EntityId getByTypeAndUuid(String type, UUID uuid) {
        return getByTypeAndUuid(EntityType.valueOf(type), uuid);
    }

    public static EntityId getByTypeAndUuid(EntityType type, String uuid) {
        return getByTypeAndUuid(type, UUID.fromString(uuid));
    }

    public static EntityId getByTypeAndUuid(EntityType type, UUID uuid) {
        switch (type) {
//            case TENANT:
//                return new TenantId(uuid);
//            case CUSTOMER:
//                return new CustomerId(uuid);
//            case USER:
//                return new UserId(uuid);
//            case DASHBOARD:
//                return new DashboardId(uuid);
//            case DEVICE:
//                return new DeviceId(uuid);
//            case ASSET:
//                return new AssetId(uuid);
//            case ALARM:
//                return new AlarmId(uuid);
//            case RULE_CHAIN:
//                return new RuleChainId(uuid);
//            case RULE_NODE:
//                return new RuleNodeId(uuid);
//            case ENTITY_VIEW:
//                return new EntityViewId(uuid);
//            case WIDGETS_BUNDLE:
//                return new WidgetsBundleId(uuid);
//            case WIDGET_TYPE:
//                return new WidgetTypeId(uuid);
        }
        throw new IllegalArgumentException("EntityType " + type + " is not supported!");
    }

}
