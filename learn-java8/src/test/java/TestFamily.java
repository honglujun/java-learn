import com.google.common.collect.Lists;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestFamily {
    @Test
    public void test0() throws Exception {
        List<Wife> wife = Lists.newArrayList();
        List<Husband> husband = Lists.newArrayList();
        for (int i = 0; i < 8; i++) {
            wife.add(new Wife(i, i + "的妻子", "000" + i));
        }

        for (int i = 0; i < 8; i++) {
            husband.add(new Husband(i, "我是" + i, "000" + i));
        }
        Long startTime = System.currentTimeMillis();
        System.out.println("当前时间:" + startTime);
        List<Family> families = Lists.newArrayList();
        // 将list转为Map,这里key一定要为唯一值
        Map<String, Wife> wifeMap = wife.stream().collect(
                Collectors.toMap(
                        w -> w.getFamilyId(),
                        w -> w,
                        (oldValue, newValue) -> newValue
                )
        );
        // 匹配家庭
        families = husband.stream().map(h ->
                toFamily(wifeMap.get(h.getFamilyId()), h)
        ).collect(Collectors.toList());
        /*for(int i=0;i<wife.size();i++){
            System.out.println("i:"+i);
            for(int j=0;j<husband.size();j++){
                System.out.println("j:"+j);
                if(wife.get(i).getFamilyId().equals(husband.get(j).getFamilyId())){
                    System.out.println("匹配成功："+i+":"+j);
                }
            }
        }*/
        System.out.println("循环完成");
        families.stream().forEach(family -> {
            System.out.println("家庭ID：" + family.getFamilyId() + ",丈夫：" + family.getHusbandName() + ",妻子：" + family.getWifeName());
        });
        Long endTime = System.currentTimeMillis();
        System.out.println("结束时间:" + endTime);
        System.out.println("------------耗时:---------" + (endTime - startTime) + "ms");
    }

    private Family toFamily(Wife wife, Husband husband) {
        Family family = new Family();
        family.setFamilyId(wife.getFamilyId());
        family.setHusbandName(husband.getHusband());
        family.setWifeName(wife.getWhoseWife());
        return family;
    }

    @Test
    public void test1() {
        //String.valueOf()
        BigDecimal c = new BigDecimal(651000);
        BigDecimal d = new BigDecimal(312);
        BigDecimal num3 = c.divide(d, 10, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(num3);
//        BigDecimal b = (new BigDecimal(651000).divide(new BigDecimal(312)).setScale(1, BigDecimal.ROUND_HALF_UP));
//        System.out.println(b);

    }

    @Test
    public void test3() {
        List<Wife> wife = Lists.newArrayList();
        List<Husband> husband = Lists.newArrayList();
        for (int i = 0; i < 8000; i++) {
            wife.add(new Wife(i, i + "的妻子", "000" + i));
        }

        for (int i = 0; i < 8000; i++) {
            husband.add(new Husband(i, "我是" + i, "000" + i));
        }
        Long startTime = System.currentTimeMillis();
        System.out.println("当前时间:" + startTime);
        List<Family> families = Lists.newArrayList();
        Map<String, Wife> wifeMap = new HashMap<>();
        for (Wife w : wife) {
            wifeMap.put(w.getFamilyId(), w);
        }
        for (Husband h : husband) {
            Wife wife1 = wifeMap.get(h.getFamilyId());
            if (wife1 != null) {
                families.add(toFamily(wife1,h));
            }
        }

        System.out.println("循环完成");
        families.stream().forEach(family -> {
            System.out.println("家庭ID：" + family.getFamilyId() + ",丈夫：" + family.getHusbandName() + ",妻子：" + family.getWifeName());
        });
        Long endTime = System.currentTimeMillis();
        System.out.println("结束时间:" + endTime);
        System.out.println("------------耗时:---------" + (endTime - startTime) + "ms");
    }

}
