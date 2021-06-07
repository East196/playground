#!/bin/sh

# 打包
mvn clean package
# 创建镜像
docker build -t nsba .

# 获取镜像id
image_id=`docker images nsba --format "{{.ID}}" | awk '{print $1}'`

echo $image_id

# 运行镜像
docker run --name nsba -p 8084:8084 -d $image_id