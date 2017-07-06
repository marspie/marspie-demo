最近需要对WebDav服务器进行操作，查找了一下，基于java的开发包主要有这几个：
slide
Jackrabbit
sardine
webdavclient4j
其中slide是apache的一个老的项目，url是http://jakarta.apache.org/slide/，这个
项目已经退休了，在它的项目主页上推荐使用Jackrabbit项目。
Jackrabbit是一个jcr实现，其中包括WebDav的服务器端和客户端。
webdavclient4j我没看，因为我先找到了sardine，项目主页是http://code.google.com/p/sardine/，
它最大的特点就是简单，使用非常简单，但项目主页也说了它并不是一个全面的实
现，它只实现了WebDav的常用命令。

下面是我在DAY的CQ5服务器（基于Jackrabbit）上的试验代码，代码创建了一个目录，然后
向这个目录上传了一个jpg文件，最后列出了这个目录的内容。
其中http://host:4502/crx/repository/crx.default是WebDav服务器的根路径，代码非常
简单，唯一需要注意的是路径为目录时，记着最后要有“/”