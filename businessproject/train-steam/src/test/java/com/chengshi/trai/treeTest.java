package com.chengshi.trai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chengshi.trai.domain.Resource;
import com.chengshi.trai.dto.ResourceDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class treeTest {

    private List<Resource> resources=new ArrayList<>();

    @Before
    public void init(){
        Resource resource1=new Resource(1,null,1,"父1");
        Resource resource2=new Resource(2,null,3,"父2");
        Resource resource3=new Resource(3,null,2,"父3");

        Resource resource4=new Resource(4,1,2,"子2-4");
        Resource resource5=new Resource(5,1,1,"子2-5");
        Resource resource6=new Resource(6,2,1,"子3-1");
        Resource resource7=new Resource(7,2,2,"子3-2");

        Resource resource8=new Resource(8,4,2,"子2-4-8");
        Resource resource9=new Resource(9,4,1,"子2-4-9");
        Resource resource10=new Resource(10,4,3,"子2-4-10");

        Resource resource11=new Resource(11,8,1,"子2-4-8-11");
        Resource resource12=new Resource(12,8,2,"子2-4-8-11");
        resources.add(resource1);
        resources.add(resource2);
        resources.add(resource3);
        resources.add(resource4);
        resources.add(resource5);
        resources.add(resource6);
        resources.add(resource7);
        resources.add(resource8);
        resources.add(resource9);
        resources.add(resource10);
        resources.add(resource11);
        resources.add(resource12);
    }

    @Test
    public  void test1(){
        resources.forEach(resource -> System.out.println(resource));
    }

    @Test
    public void test2(){
        System.out.println(JSON.toJSON(resources));
    }

    @Test
    public void test3(){
        JSONArray result = JsonUtil.listToTree(JSONArray.parseArray(JSON.toJSONString(resources)),"id","pid","children");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void test4(){
        List<ResourceDto> resourceDtos=new ArrayList<>();
        resources.forEach(resource -> {
            ResourceDto resourceDto=new ResourceDto();
            BeanUtils.copyProperties(resource,resourceDto);
            resourceDtos.add(resourceDto);
        });
        Map<Integer, List<ResourceDto>> collect = resourceDtos.stream().map(resourceDto -> {
            if (resourceDto.getPid()==null){
                resourceDto.setPid(0);
            }
            return resourceDto;
        }).collect(Collectors.groupingBy(ResourceDto::getPid));
        List<ResourceDto> resourceDtoList = collect.get(0);
        System.out.println(resourceDtoList.size());

        resourceDtoList.forEach(resourceDto -> forEach(collect, resourceDto));

        System.out.println(JSON.toJSONString(resourceDtoList));
    }

    private static void forEach(Map<Integer,List<ResourceDto>> collect, ResourceDto resourceDto) {

        List<ResourceDto> resourceDtos = collect.get(resourceDto.getId());
        if(collect.get(resourceDto.getId())!=null){
            //排序
            resourceDtos.sort(Comparator.comparing(ResourceDto::getSort));
            resourceDtos.stream().sorted(Comparator.comparing(ResourceDto::getSort)).collect(Collectors.toList());
            resourceDto.setChildren(resourceDtos);
            resourceDto.getChildren().forEach(t-> forEach(collect,t));
        }


    }

}
