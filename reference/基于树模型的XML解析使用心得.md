## 基于树模型的XML解析

## 文档对象模型（DOM）

文档对象模型（DOM：Document Object Model），将读入的XML文档转成树结构。优点是比较容易，对实现我们大多数目的来说比较充足，对于小文档读写效率高，如果大文档的话生成的树将会非常大，耗费大量内存。

文档对象模型解析器（DOM解析器）的相关接口已经被W3C标准化，org.w3c.dom下包含了这些接口。对于不同组织，有不同的实现。

Java API for XML Processing（JAXP）库使我们可以以插件形式使用这些众多解析器中的任意一个，JDK中包含了从Apache解析器导出的DOM解析器。

## 解析一个XML的步骤

### 创建DocumentBuilderFactory对象

### 创建DocumentBuilder对象



