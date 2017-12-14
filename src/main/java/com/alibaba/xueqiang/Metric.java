package com.alibaba.xueqiang;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by harlan on 2016/11/25.
 */
public enum  Metric {

  impression_pv(1,"曝光","impression_pv"),
  impression_uv(2,"独立曝光","impression_uv"),
  impression_ip(3,"曝光IP","impression_ip"),

  /**
   * 不可以直接调用BG
   */
  impression_frequency(4,"曝光频次",""),

  click_pv(5,"点击","click_pv"),
  click_uv(6,"独立点击","click_uv"),
  click_ip(7,"点击IP","click_ip"),

  /**
   * 不可以直接调用BG
   */
  click_frequency(8,"点击频次",""),
  /**
   * 不可以直接调用BG
   */
  click_rate(9,"点击率",""),


  click_pv_os(101,"点击操作系统分布","click_pv_os"),
  click_uv_os(102,"独立点击操作系统分布","click_uv_os"),
  click_ip_os(103,"IP点击操作系统分布","click_ip_os"),
  click_pv_dt(104,"点击设备分布","click_pv_dt"),
  click_uv_dt(105,"独立点击设备分布","click_uv_dt"),
  click_ip_dt(106,"IP点击设备分布","click_ip_dt"),
  click_pv_province(107,"点击省份分布","click_pv_province"),


  click_uv_province(108,"独立点击省份分布","click_uv_province"),
  click_ip_province(109,"IP点击省份分布","click_ip_province"),


  impression_pv_os(130,"曝光操作系统分布","impression_pv_os"),
  impression_uv_os(131,"独立曝光操作系统分布","impression_uv_os"),
  impression_ip_os(132,"曝光IP操作系统分布","impression_ip_os"),
  impression_pv_dt(133,"曝光设备类型分布","impression_pv_dt"),
  impression_uv_dt(134,"独立曝光设备类型分布","impression_uv_dt"),
  impression_ip_dt(135,"曝光IP设备类型分布","impression_ip_dt"),
  impression_pv_province(136,"曝光省份分布","impression_pv_province"),
  impression_uv_province (137,"独立曝光省份分布","impression_uv_province"),
  impression_ip_province(138,"曝光IP省份分布","impression_ip_province"),



  click_uv_total(139,"全部目标人群独立曝光","click_uv_total"),
  click_pv_total(140,"全部目标人群曝光","click_pv_total"),
  click_ip_total(141,"全部目标人群曝光IP","click_ip_total"),
  impression_uv_total(142,"全部目标人群独立点击","impression_uv_total"),
  impression_pv_total (143,"全部目标人群点击","impression_pv_total"),
  impression_ip_total(144,"全部目标人群点击IP","impression_ip_total");


  private Metric (int id,String name ,String code,Metric... metrics){
    this.id = id;
    this.code = code;
    this.name = name;
  }

  private Integer id;
  private String code;
  private String name;



  public String toCode(){return code;}
  public int toInt(){return id;}
  public String toName(){return name;}


  public static Metric parse(int id){
    for (Metric e : Metric.values()){
      if(e.toInt() == id){
        return e;
      }
    }
    return null;
  }

  public static Metric parse(String code){
    for (Metric e : Metric.values()){
      if(e.toCode().equals(code)){
        return e;
      }
    }
    return null;
  }


  public static Metric[] parseProvince(Metric metric){
    Metric[] m = null;
    switch (metric){
      case impression_pv:
        m = new Metric[]{impression_pv_province};
        break;
      case impression_uv:
        m = new Metric[]{impression_uv_province};
        break;
      case impression_ip:
        m = new Metric[]{impression_ip_province};
        break;
      case click_pv:
        m = new Metric[]{click_pv_province};
        break;
      case click_uv:
        m = new Metric[]{click_uv_province};
        break;
      case click_ip:
        m = new Metric[]{click_ip_province};
        break;

      case click_frequency:
        m = new Metric[]{click_pv_province,click_uv_province};
        break;
      case click_rate:
        m = new Metric[]{click_pv_province,impression_pv_province};
        break;
      case impression_frequency:
        m = new Metric[]{impression_pv_province,impression_uv_province};
        break;

      default:
        m =new Metric[]{ metric};
    }
    return m;
  }


  public static Metric[] parseDevType(Metric metric){
    Metric[] m = null;
    switch (metric){
      case impression_pv:
        m = new Metric[]{impression_pv_dt};
        break;
      case impression_uv:
        m = new Metric[]{impression_uv_dt};
        break;
      case impression_ip:
        m = new Metric[]{impression_ip_dt};
        break;
      case click_pv:
        m = new Metric[]{click_pv_dt};
        break;
      case click_uv:
        m = new Metric[]{click_uv_dt};
        break;
      case click_ip:
        m = new Metric[]{click_ip_dt};
        break;

      case click_frequency:
        m = new Metric[]{click_pv_dt,click_uv_dt};
        break;
      case click_rate:
        m = new Metric[]{click_pv_dt,impression_pv_dt};
        break;
      case impression_frequency:
        m = new Metric[]{impression_pv_dt,impression_uv_dt};
        break;
      default:
        m = new Metric[]{metric};
    }
    return m;
  }


  public static Metric parseOs(Metric metric){
    Metric m = null;
    switch (metric){
      case impression_pv:
        m = impression_pv_os;
        break;
      case impression_uv:
        m = impression_uv_os;
        break;
      case impression_ip:
        m = impression_ip_os;
        break;
      case click_pv:
        m = click_pv_os;
        break;
      case click_uv:
        m = click_uv_os;
        break;
      case click_ip:
        m = click_ip_os;
        break;
      default:
        m = metric;
    }
    return m;
  }



  public static Metric[] parseTrend(Metric metric){
    Metric[] m = null;
    switch (metric){
      case impression_pv:
      case impression_uv:
      case impression_ip:
      case click_pv:
      case click_uv:
      case click_ip:
        m = new Metric[]{metric};
        break;

      case click_frequency:
        m = new Metric[]{click_pv,click_uv};
        break;
      case click_rate:
        m = new Metric[]{click_pv,impression_pv};
        break;
      case impression_frequency:
        m = new Metric[]{impression_pv,impression_uv};
        break;
      default:
        m = new Metric[]{metric};
    }
    return m;
  }

  public static Metric[] parseTrendAllTarget(Metric metric){
    Metric[] m = null;
    switch (metric){
      case impression_pv:
        m = new Metric[]{Metric.impression_pv_total};
        break;
      case impression_uv:
        m = new Metric[]{Metric.impression_uv_total};
        break;
      case impression_ip:
        m = new Metric[]{Metric.impression_ip_total};
        break;
      case click_pv:
        m = new Metric[]{Metric.click_pv_total};
        break;
      case click_uv:
        m = new Metric[]{Metric.click_uv_total};
        break;
      case click_ip:
        m = new Metric[]{Metric.click_ip_total};
        break;
      case click_frequency:
        m = new Metric[]{click_pv_total,click_uv_total};
        break;
      case click_rate:
        m = new Metric[]{click_pv_total,impression_pv_total};
        break;
      case impression_frequency:
        m = new Metric[]{impression_pv_total,impression_uv_total};
        break;
      default:
        m = new Metric[]{metric};
    }
    return m;
  }





  public static Set<Metric> parseList(Metric[] metrics){
    Set<Metric> set = new HashSet<>();
    for (Metric metric : metrics){
      switch (metric){
        case impression_pv:
        case impression_uv:
        case impression_ip:
        case click_pv:
        case click_uv:
        case click_ip:
          set.add(metric);
          break;
        case click_frequency:
          set.add(click_pv);
          set.add(click_pv);
          break;
        case click_rate:
          set.add(click_pv);
          set.add(impression_pv);
          break;
        case impression_frequency:
          set.add(impression_pv);
          set.add(impression_uv);
          break;
        default:
          set.add(metric);
      }
    }
    return set;
  }




  private final static Map<String,int[]> rule = new HashMap<>();
  static {
    rule.put("sum",new int[]{impression_pv.toInt(),impression_uv.toInt(),impression_ip.toInt(),click_pv.toInt(),click_uv.toInt(),click_ip.toInt()});
    rule.put("avg",new int[]{impression_frequency.toInt(),click_frequency.toInt(),click_rate.toInt()});
  }

  public static Map<String, int[]> getRule() {
    return rule;
  }

  private final static Map<String,Metric[]> defMetrics= new HashMap<>();
  static {
    defMetrics.put("sum",new Metric[]{impression_pv,impression_uv,impression_ip,click_pv,click_uv,click_ip});
    defMetrics.put("avg",new Metric[]{impression_frequency,click_frequency,click_rate});
  }

  public static Map<String, Metric[]> getDefMetrics() {
    return defMetrics;
  }

  public static void main(String[] args) {
    Map<String,Object> map = new HashMap<>();
    map.put("sdf",Metric.click_ip);
    System.out.println(new Gson().toJson(map));
  }
}
