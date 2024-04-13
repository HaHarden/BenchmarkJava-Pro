# SASTing-testcase-pro

This module is a SpringBoot project, with its test cases primarily focusing on the perspective of **static analysis**. You can conveniently approach the validation of security vulnerabilities within programs from the standpoint of **static analysis**.

In the testcases, annotate the vulnerability type in comment form at the line containing the sink point. An exclamation mark `!` at the beginning indicates TN (True Negative), whereas its absence signifies TP (True Positive).

**The following is an example of the comment :** 

- XSS: denotes the line containing the sink point for an XSS vulnerability.
- !XSS: denotes that the sink point for an XSS vulnerability should not be present on this line.

# 公众号（WeChat OA）

**推荐阅读：**

- [抽丝剥茧代码属性图CPG-第一弹：CPG介绍](https://mp.weixin.qq.com/s/JieX9Q4KLDAfH4s_FCvoXQ)
- [抽丝剥茧代码属性图CPG-第二弹：CPG中的DFG-1](https://mp.weixin.qq.com/s/GtnILXWXaPWKmnammGaMuQ)
- [抽丝剥茧代码属性图CPG-第三弹：CPG中的DFG-2](https://mp.weixin.qq.com/s/x7cAdgn6qx13vw_ODQNb7g)
- [SAST-短小精悍的Benchmark](https://mp.weixin.qq.com/s/s9k6YTTrf-Wj0A_vAce4Fg)

公众号ID：`CodeAnalyzer`，公众号名称：`CodeAnalyzer Ultra`

公众号二维码：

![Alt text](src/main/resources/CodeAnalyzer_ewm.jpg)