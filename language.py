#coding:utf-8
# 输出正确数量的 tab ，使 exec 中的代码正常运行
def out_tab(count):
	string = ''
	for i in range(count):
		string += '\t'
	return string

# 获得下一个替换词，返回 替换词 和 被替换了替换词的原字符串
def get_next_key(string):
	start = string.find('[<')
	if start != -1:
		end = string[start + 2:].find('>]')
		if end != -1:
			end = start + 2 + end
			return string[start + 2:end], string[:start] + '@#$%^&^%$#' + string[end + 2:]
	return False, string

# 获得所有替换词，返回替换词数组 和 被替换了替换词的原字符串
def get_all_key(string):
	keys = []
	key, string = get_next_key(string)
	while key != False:
		keys.append(key)
		key, string = get_next_key(string)
	return keys, string

# 单行重复替换（用于 exec 内部）
def many(n, *intervals):
	string = ''
	for i, nn in enumerate(n):
		for j, interval in enumerate(intervals):
			if i == len(n) - 1 and j == len(intervals) - 1:
				string += str(nn)
			else:
				string += str(nn) + interval
	return string

#当字符串中存在需要替换的数组时，获得其数组长度
def get_list_num(keys):
	list_num = 1
	for key in keys:
		if list_num == 1:
			if type(key) == list:
				list_num = len(key)
		else:
			if type(key) == list and len(key) != list_num:
				exit("error: 存在多个不同长度的数组在同一行输出")
	return list_num

#让字符串变得可以使用 exec 运行，可输入命令对每一行进行操作
def make_strings_run(strings, command_head, commmand_foot = ''):
	out_strs = []
	count = 0
	for i, s in enumerate(strings):
		clean_s = s.strip()
		if clean_s[:4] != '-->>':
			keys, string = get_all_key(s)
			keys_str = '[' + ', '.join(keys) + ']'
			for key in keys:
				string = string.replace("@#$%^&^%$#", "[<(str(" + key + "[list_i]) if type(" + key + ") == list else str(" + key + "))>]", 1)
			if len(keys) != 0:
				out_strs.append(out_tab(count) + 'for list_i in range(get_list_num(' + keys_str + ')):')
				out_strs.append(out_tab(count) + '\t' + command_head + "'''" + string.replace('[<', " '''[:-1]+").replace('>]', "+'''") + "\\n '''[:-1]" + commmand_foot)
			else:
				out_strs.append(out_tab(count) + command_head + "'''" + s + "\\n '''[:-1]" + commmand_foot)
		else:
			if clean_s[-1] == ':':
				count += 1
			if clean_s[-4:] =='pass':
				count -= 1
			out_strs.append(s[4:])
	return '\n'.join(out_strs)	




if __name__ == '__main__':
	string = """
line1
line2
line3'
-->>if a == 'ok':
line4[<a>]
line5[<n>][<a>]
line7[<many(h, ', ', '; ')>]
-->>	pass
-->>else:
line6
-->>	pass"""
	dictionary = {'A': 'Ok', 'a': 'ok', 'n':['one', 'two'], 'h':['UUU', 'KKK']}
	dictionary['many'] = many
	dictionary['get_list_num'] = get_list_num
	print make_strings_run(string.split('\n'), 'print ')
	exec make_strings_run(string.split('\n'), 'print ') in dictionary
	print 'ok'







