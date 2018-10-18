#coding:utf-8
from language import *

class Data:
	def __init__(self, string = '', c_string = '-'):
		self.string = string
		self.c_string = c_string
		if string.find('(') != -1 and string.find(')') != -1:
			self.name = string[:string.find('(')]
			self.c = c_string[:c_string.find('(')]
			include = string[string.find('(') + 1:string.find(')')].split(',')
			c_include = c_string[c_string.find('(') + 1:c_string.find(')')].split(',')
			self.n = [Data(include[i].strip(), c_include[i].strip()) for i, inc in enumerate(include)]
		else:
			self.name = string
			self.c = c_string
			self.n = False
	def __str__(self):
		return self.name

	# def fortest(self):
	# 	return '[' + self.name + ',' + self.c + (','.join([nn.fortest() for nn in self.n])if self.n != False else '') + ']'

# 通过字符串数组写文件
def write(file, strings, type = 'w'):
	with open(file, type) as f:
		for string in strings:
			f.write(string)

# 通过读文件返回字符串数组
def read(file, type = 'r'):
	with open(file, type) as f:
		return f.readlines()
	return []

# 使字符串每个单词的首字母小写（单词需要用空格连接）
def get_lowercase(input):
	input = input.strip()
	output = ' ' + input
	for x in range(65, 65 + 26):
		output = output.replace(' ' + chr(x), ' ' + chr(x + 32))
	return output[1:]

# 使字符串每个单词的首字母大写（单词需要用空格连接）
def get_biggercase(input):
	input = input.strip()
	output = ' ' + input
	for x in range(95, 95 + 26):
		output = output.replace(' ' + chr(x), ' ' + chr(x - 32))
	return output[1:]

# 设置一个键值对（同时设置大小写）
def set_one_dict(dictionary, key, input):
	if type(input) == type(Data()):
		dictionary[key] = Data(get_lowercase(input.string), input.c_string)
		dictionary[key.title()] = Data(get_biggercase(input.string), input.c_string)
	elif type(input) == list:
		dictionary[key] = [Data(get_lowercase(_input.string), _input.c_string) for _input in input]
		dictionary[key.title()] = [Data(get_biggercase(_input.string), _input.c_string) for _input in input]
	return dictionary

# 获得一个替换字典
# TODO 需要改写成更灵活的
def get_dict(input):
	input = input.split('\n');
	dictionary = {}
	dictionary = set_one_dict(dictionary, 'm', Data(input[0].split()[0], input[1].split()[0]))
	dictionary = set_one_dict(dictionary, 'a', Data(input[0].split()[1], input[1].split()[1]))
	dictionary = set_one_dict(dictionary, 'id', Data(input[0].split()[2], input[1].split()[2]))
	dictionary = set_one_dict(dictionary, 'f', Data(input[0].split()[-1], input[1].split()[-1]))
	items = [input[0].split()[3:-1], input[1].split()[3:-1]]
	dictionary = set_one_dict(dictionary, 'n', [Data(items[0][i], items[1][i]) for i,item in enumerate(items[0])])
	for mo in input[2].split():
		dictionary = set_one_dict(dictionary, mo[:mo.find('(')], [dictionary['n'][int(i)] for i in mo[mo.find('(') + 1:-1].split(',')])
	return dictionary


import os
# 获得某文件夹下的所有文件
# TODO 需要改写成适合全平台
def file_name(file_dir):
	for root, dirs, files in os.walk(file_dir):  
		# print(root) #当前目录路径  
		# print(dirs) #当前路径下所有子目录  
		# print(files) #当前路径下所有非目录子文件
		files.remove('.DS_Store') # macOS 用以存放文件夹的显示属性的文件，比如文件图标的摆放位置
		return files

# 替换模板成需要的文件
def replace(input, dictionary):
	in_strs = []
	out_strs = []
	if isinstance(input, (list)):
		in_strs = input	  
	elif check('model/' + input):
		with open('model/' + input, 'r') as f: 
			in_strs = f.readlines()
	for string in in_strs:
		out_strs.append(string.strip('\n').strip('\r'))

	in_strs = out_strs
	out_strs = []
	dictionary['many'] = many
	dictionary['get_list_num'] = get_list_num	
	dictionary['out_strs'] = []
	strings_run = make_strings_run(in_strs, 'out_strs.append(', ')')
	exec strings_run in dictionary
	out_strs = dictionary['out_strs']

	return out_strs

# 获得输入和完成替换
def make_all():
	print '请输入表的相关信息，如下格式：'
	print '第一行：项目名 表名 属性（Id 其他属性 Flag）'
	print '第二行：各种中文'
	print '第三行：附加功能'
	print '请输入：'
	input = ''
	for i in range(3):
		input += raw_input() + '\n'
	dictionary = get_dict(input)
	for f in file_name('model'):
		write('output/' + str(dictionary['A']) + f, replace(read('model/' + f), dictionary))

if __name__ == '__main__':
	make_all()

'''
wtz WTZuserList ID realname company industry job mobilephone email Createtime Flag
- 用户 ID 姓名 公司 行业 职务 手机 邮箱 创建时间 -
like(1,2,3,4,5)


wtz WTZoperator id username realname userpwd usertype(0201,0202) createtime createuser flag
- 管理 ID 用户名 真实姓名 密码 用户类型(系统管理员,浏览用户) 创建时间 创建人 -
like(0,1,3)

ok OKoooo id name pwd kind(0101,0102,0103) flag
- 欧克欸 ID 名字 密码 用户类型(人,猴,猪) -
like(0,2)









'''





