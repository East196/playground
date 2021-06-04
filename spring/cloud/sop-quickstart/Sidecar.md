[TOC]

## 参考
> [SOP](https://gitee.com/durcframework/SOP)

## Tips
1. OpenServiceConfig.java是必须的，可以注册源信息到注册中心，从而被Admin感知
2. 关闭gateway直接用rest访问(没有成功)
```yml
sop:
  restful:
    enable: false
```