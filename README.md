# Fast Repalce ( 快速生成文件器 )
**使用的 python 版本为 2.x**
python run.py 运行脚本
```
请输入表的相关信息，如下格式：
第一行：项目名 表名 属性（Id 其他属性 Flag）
第二行：各种中文
第三行：附加功能
请输入：
Name ListName Id OneOne TwoTwo ThreeThree Sex(0,1) Flag
- 表名 ID 属性一 属性二 属性三 性别(男,女) -
like(1,2,3)
```
输入如上格式后，将对应 model 中各个文件生成对应文件，替换成输入的关键字，适用于开发时用到相似代码快速生成。
- [x] 更改 model 适合于你自己的项目
- [x] 在 model 文件中嵌套 python 代码以满足复杂需求
- [x] 替换区分 str 和 list，前者直接替换，后者将所在行重复 list 的个数遍
