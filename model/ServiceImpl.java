package com.[<m>].serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.[<m>].dao.[<A>]Mapper;
import com.[<m>].pojo.[<A>];
import com.[<m>].service.[<A>]Service;
@Service
public class [<A>]ServiceImpl implements [<A>]Service{
	@Resource
	private [<A>]Mapper [<a>]Mapper;

	@Override
	public List<[<A>]> [<A>]Sel(Map<String, Object> map) {
		return [<a>]Mapper.[<A>]Sel(map);
	}

	@Override
	public List<[<A>]> [<A>]Size([<A>] [<a>]) {
		return [<a>]Mapper.[<A>]Size([<a>]);
	}

	@Override
	public Integer [<A>]Del([<A>] [<a>]) {
		return [<a>]Mapper.[<A>]Del([<a>]);
	}

	@Override
	public Integer [<A>]Add([<A>] [<a>]) {
		return [<a>]Mapper.[<A>]Add([<a>]);
	}

	@Override
	public [<A>] [<A>]Info([<A>] [<a>]) {
		return [<a>]Mapper.[<A>]Info([<a>]);
	}

	@Override
	public Integer [<A>]Mod([<A>] [<a>]) {
		return [<a>]Mapper.[<A>]Mod([<a>]);
	}

}
