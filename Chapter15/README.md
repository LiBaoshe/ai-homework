# 第十五章作业
## 作业内容
1.使用 FastGPT 在线版完成雪茄百科和 AI 行政助理应用的开发，并优化问题分类描述和提示词，使得 AI 行政助理可以高效回答新员工提问（可适当补充公司手册内容）。    
2.（可选）使用 Docker Compose 完成 FastGPT 私有化部署，并使用私有化版本完成 AI 行政助理的开发。
## 在线版雪茄百科
### 创建雪茄知识库  
<img width="1818" height="1050" alt="image" src="https://github.com/user-attachments/assets/cea16d35-0aa5-4780-a325-e0309c0931de" />  

### 创建雪茄百科问答应用  
<img width="3840" height="2088" alt="image" src="https://github.com/user-attachments/assets/d90029e4-8879-4562-8206-b9419ff13ae5" />  

## AI行政助理应用
### 创建公司手串知识库  
<img width="1813" height="1025" alt="image" src="https://github.com/user-attachments/assets/e6a806ac-d945-4859-a3df-af53ae3bc300" />  

### 创建AI行政助理应用  
<img width="3840" height="2088" alt="image" src="https://github.com/user-attachments/assets/295a50be-a857-4cc6-bd79-76c624466dd0" />  

## FastGPT 私有化部署  
### 使用docker-compose安装运行FastGPT  
创建fastgpt目录，下载配置文件：  
mkdir fastgpt  
cd fastgpt  
curl -O https://raw.githubusercontent.com/labring/FastGPT/main/projects/app/data/config.json  
curl -o docker-compose.yml https://raw.githubusercontent.com/labring/FastGPT/main/deploy/docker/docker-compose-pgvector.yml  
修改相关配置后，启动docker：  
docker-compose up -d  
运行如图：  
<img width="2547" height="634" alt="image" src="https://github.com/user-attachments/assets/abb895a5-13a1-4ef9-994e-728a215c8b20" />  
### 访问私有FastGPT，配置模型  
模型渠道：  
<img width="1955" height="625" alt="image" src="https://github.com/user-attachments/assets/e54b2fde-c3c4-4906-b95c-3c315d7b89df" />  
调用日志：  
<img width="1935" height="1185" alt="image" src="https://github.com/user-attachments/assets/bfff8e0c-cd0c-4a60-939d-ada4037db06c" />  
模型配置：  
<img width="1941" height="1303" alt="image" src="https://github.com/user-attachments/assets/02392ca2-fac9-4da8-a044-b80435c33326" />  
可用模型：  
<img width="1953" height="1155" alt="image" src="https://github.com/user-attachments/assets/21ab9761-1cd1-426b-9eee-5e561e5aac57" />  

