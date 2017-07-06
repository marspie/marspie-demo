FROM tomcat:8
MAINTAINER alex.yao <yaochangdong@huaqi.info>

ADD marspie.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]

EXPOSE 8080