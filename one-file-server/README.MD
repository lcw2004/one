# 文件存储简介
目前提供了三种存储策略：
1. 本地存储
2. 阿里云OSS存储
3. 本地存储 + 阿里云OSS存储

## 配置

```
one:
  file:
    type: oss
    temp: D:\Files\temp
    local:
      path: D:\Files
    oss:
      endpoint: oss-cn-shenzhen.aliyuncs.com
      accessKeyId: LTAIR9ovIy10ZFNd
      accessKeySecret: 987SmrPOuQoEF46KqMN8gXdhABH6GD
      bucketName: bairui-dev
	local-oss:
	  max-time: 
```

## 本地存储

`one.file.type` 的值配置为 `local`，然后配置 `one.file.local` 下面的配置

## 阿里云OSS存储

`one.file.type` 的值配置为 `oss`，然后配置 `one.file.oss` 下面的配置

## 本地存储 + 阿里云OSS存储

`one.file.type` 的值配置为 `loca-oss`，然后配置 `one.file.local` 和 `one.file.oss` 下面的配置。

这种存储方式是先在本地存储，然后定时器会在计算机空闲的时候将本地存储的文件同步到OSS上，这种方式较上面两种方式稍微复杂一些。但是上传速度会比OSS存储快一些。