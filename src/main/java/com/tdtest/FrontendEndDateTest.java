package com.tdtest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkingdata.mc.utils.DateUtil;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by tend on 2018/2/6.
 */
public class FrontendEndDateTest {
    private static  final String expireCampaignIds = "{\n" +
            "\t\"j4v4Qh5u\": [\"2018-02-04 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"c1H739US\": [\"2018-01-12 00:00:00\", \"2018-02-12 23:59:59\"],\n" +
            "\t\"KmL01TTZ\": [\"2017-12-26 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"ZMKzycEl\": [\"2018-02-02 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"VKUwF2PC\": [\"2018-02-05 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"GK2RasuB\": [\"2018-01-12 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"DeGa23C7\": [\"2018-01-12 00:00:00\", \"2018-02-12 23:59:59\"],\n" +
            "\t\"BssYc2y3\": [\"2018-01-17 00:00:00\", \"2018-02-21 23:59:59\"],\n" +
            "\t\"HLUuVjcJ\": [\"2018-01-17 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"QkVTv76Z\": [\"2018-02-01 00:00:00\", \"2018-03-04 23:59:59\"],\n" +
            "\t\"DIzJmsm4\": [\"2017-09-06 00:00:00\", \"2032-10-31 23:59:59\"],\n" +
            "\t\"kWEwIWtn\": [\"2017-08-30 00:00:00\", \"2018-08-31 23:59:59\"],\n" +
            "\t\"YAVLIirt\": [\"2017-10-12 00:00:00\", \"2018-11-30 23:59:59\"],\n" +
            "\t\"0RHsC7vn\": [\"2017-09-01 00:00:00\", \"2018-10-31 23:59:59\"],\n" +
            "\t\"PlSoL30e\": [\"2018-01-12 00:00:00\", \"2018-02-12 23:59:59\"],\n" +
            "\t\"d9AB8kkV\": [\"2018-01-27 00:00:00\", \"2018-02-07 23:59:59\"],\n" +
            "\t\"Waa97hOO\": [\"2017-10-16 00:00:00\", \"2018-05-31 23:59:59\"],\n" +
            "\t\"wz1UkoN9\": [\"2017-12-29 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"NsAdSUVX\": [\"2017-10-17 00:00:00\", \"2018-05-31 23:59:59\"],\n" +
            "\t\"LTkiXYSA\": [\"2018-01-03 00:00:00\", \"2019-01-01 23:59:59\"],\n" +
            "\t\"mqdM1mLC\": [\"2018-01-18 00:00:00\", \"2018-03-31 23:59:59\"],\n" +
            "\t\"UcC7dnAb\": [\"2018-01-26 00:00:00\", \"2018-02-07 23:59:59\"],\n" +
            "\t\"3usTERwU\": [\"2017-10-16 00:00:00\", \"2018-06-01 23:59:59\"],\n" +
            "\t\"R2KeTrfD\": [\"2017-12-21 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"G275xFsQ\": [\"2018-01-16 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"mVhcgWX6\": [\"2017-12-29 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"wxyqfGvy\": [\"2018-01-05 00:00:00\", \"2018-02-06 23:59:59\"],\n" +
            "\t\"0FzYe9MX\": [\"2018-01-05 00:00:00\", \"2018-02-06 23:59:59\"],\n" +
            "\t\"BiItLfim\": [\"2018-02-01 00:00:00\", \"2018-02-01 23:59:59\"],\n" +
            "\t\"cWaEYDBJ\": [\"2018-01-17 00:00:00\", \"2018-02-20 23:59:59\"],\n" +
            "\t\"bsY9FFTg\": [\"2018-02-06 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"vu5chTHA\": [\"2017-10-10 00:00:00\", \"2018-11-01 23:59:59\"],\n" +
            "\t\"NQ6Eg8PJ\": [\"2018-01-12 00:00:00\", \"2018-02-12 23:59:59\"],\n" +
            "\t\"1vQveVXQ\": [\"2018-01-30 00:00:00\", \"2018-02-07 23:59:59\"],\n" +
            "\t\"Vz4ianyu\": [\"2018-02-01 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"16jxV27k\": [\"2018-01-05 00:00:00\", \"2018-02-05 23:59:59\"],\n" +
            "\t\"2QXjoy3m\": [\"2018-01-02 00:00:00\", \"2018-02-02 23:59:59\"],\n" +
            "\t\"42uT78tw\": [\"2018-01-05 00:00:00\", \"2018-02-06 23:59:59\"],\n" +
            "\t\"kX0kfN3l\": [\"2018-02-06 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"tX1VH3cI\": [\"2018-01-05 00:00:00\", \"2018-02-06 23:59:59\"],\n" +
            "\t\"xrmKXFtq\": [\"2018-01-02 00:00:00\", \"2018-02-02 23:59:59\"],\n" +
            "\t\"1cVQle3A\": [\"2018-01-05 00:00:00\", \"2018-02-05 23:59:59\"],\n" +
            "\t\"11Mt4OmV\": [\"2018-01-26 00:00:00\", \"2018-02-07 23:59:59\"],\n" +
            "\t\"o51aiR7K\": [\"2017-12-29 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"Ce5U9unO\": [\"2018-01-05 00:00:00\", \"2018-02-14 23:59:59\"],\n" +
            "\t\"08KfP3kw\": [\"2017-08-31 00:00:00\", \"2018-09-30 23:59:59\"],\n" +
            "\t\"lpKju4xW\": [\"2017-12-29 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"HUqeBX5j\": [\"2017-08-15 00:00:00\", \"2027-09-30 23:59:59\"],\n" +
            "\t\"toV8w15J\": [\"2018-01-05 00:00:00\", \"2018-02-06 23:59:59\"],\n" +
            "\t\"Dx9pbBq8\": [\"2018-02-02 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"DYzs4Ddv\": [\"2018-01-12 00:00:00\", \"2018-02-12 23:59:59\"],\n" +
            "\t\"MzQxnGhk\": [\"2017-10-17 00:00:00\", \"2018-05-31 23:59:59\"],\n" +
            "\t\"YBgbH171\": [\"2017-10-16 00:00:00\", \"2018-04-30 23:59:59\"],\n" +
            "\t\"7ATiFE5u\": [\"2017-12-29 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"cw01qh6O\": [\"2018-01-05 00:00:00\", \"2018-02-07 23:59:59\"],\n" +
            "\t\"9hO22fbx\": [\"2018-01-17 00:00:00\", \"2018-02-20 23:59:59\"],\n" +
            "\t\"LkKfxm8T\": [\"2018-01-05 00:00:00\", \"2018-02-06 23:59:59\"],\n" +
            "\t\"DQiLhKOw\": [\"2017-12-29 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"QkIisLsg\": [\"2018-02-01 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"hqSeVoGN\": [\"2017-11-13 00:00:00\", \"2018-03-31 23:59:59\"],\n" +
            "\t\"NusN5uGL\": [\"2018-01-05 00:00:00\", \"2018-02-05 23:59:59\"],\n" +
            "\t\"0aDML7Nx\": [\"2017-07-28 00:00:00\", \"2018-07-31 23:59:59\"],\n" +
            "\t\"mpVXiskl\": [\"2018-02-06 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"ebTKEr7A\": [\"2018-01-17 00:00:00\", \"2018-02-20 23:59:59\"],\n" +
            "\t\"2tDBZcla\": [\"2018-01-05 00:00:00\", \"2018-02-14 23:59:59\"],\n" +
            "\t\"PXqBPOsT\": [\"2018-01-12 00:00:00\", \"2018-02-12 23:59:59\"],\n" +
            "\t\"JCMsED5M\": [\"2017-12-27 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"TlrR7z5I\": [\"2017-10-16 00:00:00\", \"2018-05-31 23:59:59\"],\n" +
            "\t\"HWOIxArx\": [\"2018-01-28 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"xf1dPxmH\": [\"2018-01-26 00:00:00\", \"2018-02-07 23:59:59\"],\n" +
            "\t\"UnAfnhb1\": [\"2017-10-16 00:00:00\", \"2018-03-31 23:59:59\"],\n" +
            "\t\"YEzcgZOj\": [\"2017-10-16 00:00:00\", \"2018-05-31 23:59:59\"],\n" +
            "\t\"jkTMXSwA\": [\"2017-12-29 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"Mzp3JaU6\": [\"2018-01-05 00:00:00\", \"2018-02-05 23:59:59\"],\n" +
            "\t\"yhSVf8Dn\": [\"2017-12-29 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"x0JeQa4K\": [\"2017-12-27 00:00:00\", \"2018-01-31 23:59:59\"],\n" +
            "\t\"INrZStDe\": [\"2018-01-28 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"keD75vdh\": [\"2018-01-17 00:00:00\", \"2018-02-20 23:59:59\"],\n" +
            "\t\"KFw7X7Sw\": [\"2018-01-02 00:00:00\", \"2018-02-02 23:59:59\"],\n" +
            "\t\"GHzxj5NA\": [\"2017-10-16 00:00:00\", \"2018-04-30 23:59:59\"],\n" +
            "\t\"hhPdNm3x\": [\"2017-10-16 00:00:00\", \"2018-04-30 23:59:59\"],\n" +
            "\t\"Cxh94h8Y\": [\"2018-01-17 00:00:00\", \"2018-02-20 23:59:59\"],\n" +
            "\t\"JK46tWOI\": [\"2018-02-01 00:00:00\", \"2018-02-28 23:59:59\"],\n" +
            "\t\"gNMJn0z0\": [\"2017-12-01 00:00:00\", \"2018-02-16 23:59:59\"],\n" +
            "\t\"PbawiaBT\": [\"2018-01-17 00:00:00\", \"2018-02-20 23:59:59\"],\n" +
            "\t\"OJOT5FuN\": [\"2018-01-18 00:00:00\", \"2018-03-31 23:59:59\"],\n" +
            "\t\"vfZQy7XO\": [\"2018-01-02 00:00:00\", \"2018-02-14 23:59:59\"],\n" +
            "\t\"yU7Lo0Cx\": [\"2017-09-11 00:00:00\", \"2018-10-31 23:59:59\"],\n" +
            "\t\"EO3TCLAE\": [\"2018-02-01 00:00:00\", \"2018-02-07 23:59:59\"]\n" +
            "}";

    private static final  String campaignIdsMuch = "[\n" +
            "\"QkIisLsg.zip|Sun Feb 04 10:45:21 CST 2018|46414386|44.3 MB\",\n" +
            "\"wz1UkoN9.zip|Tue Jan 02 23:40:41 CST 2018|989639657|944 MB\",\n" +
            "\"x0JeQa4K.zip|Tue Jan 02 17:26:09 CST 2018|2288415906|2.1 GB\",\n" +
            "\"LkKfxm8T.zip|Fri Jan 05 12:01:16 CST 2018|195747559|187 MB\",\n" +
            "\"c1H739US.zip|Fri Jan 12 19:01:31 CST 2018|1185452796|1.1 GB\",\n" +
            "\"PbawiaBT.zip|Wed Jan 17 15:46:41 CST 2018|15805873|15.1 MB\",\n" +
            "\"HWOIxArx.zip|Sun Jan 28 10:05:49 CST 2018|564797555|539 MB\",\n" +
            "\"JK46tWOI.zip|Thu Feb 01 16:44:53 CST 2018|808361973|771 MB\",\n" +
            "\"SxFIBAQs.zip|Mon Feb 05 17:59:03 CST 2018|805306368|768 MB\",\n" +
            "\"KmL01TTZ.zip|Tue Jan 02 23:12:06 CST 2018|102621389|97.9 MB\",\n" +
            "\"gNMJn0z0.zip|Mon Feb 05 21:53:29 CST 2018|24723|24.1 KB\",\n" +
            "\"lpKju4xW.zip|Tue Jan 02 23:53:22 CST 2018|28754815|27.4 MB\",\n" +
            "\"xrmKXFtq.zip|Tue Jan 02 13:30:02 CST 2018|481670998|459 MB\",\n" +
            "\"2QXjoy3m.zip|Tue Jan 02 14:01:10 CST 2018|1082504590|1.0 GB\",\n" +
            "\"M5RT14BP.zip|Wed Jan 03 16:34:25 CST 2018|93444458|89.1 MB\",\n" +
            "\"LPETUp2I.zip|Fri Jan 05 12:02:31 CST 2018|167192722|159 MB\",\n" +
            "\"DYzs4Ddv.zip|Fri Jan 12 18:12:46 CST 2018|353345515|337 MB\",\n" +
            "\"DeGa23C7.zip|Fri Jan 12 18:18:30 CST 2018|235644143|225 MB\",\n" +
            "\"PlSoL30e.zip|Fri Jan 12 20:40:43 CST 2018|2814102651|2.6 GB\",\n" +
            "\"cWaEYDBJ.zip|Wed Jan 17 15:48:50 CST 2018|783090028|747 MB\",\n" +
            "\"INrZStDe.zip|Sun Jan 28 10:59:31 CST 2018|1580015843|1.5 GB\",\n" +
            "\"Vz4ianyu.zip|Thu Feb 01 17:30:44 CST 2018|1210497141|1.1 GB\",\n" +
            "\"OqtUzroC.zip|Mon Feb 05 20:17:39 CST 2018|1879048192|1.8 GB\",\n" +
            "\"jvr6PLCH.zip|Fri Jan 05 12:02:40 CST 2018|540822985|516 MB\",\n" +
            "\"bsY9FFTg.zip|Tue Feb 06 12:31:18 CST 2018|137727548|131 MB\",\n" +
            "\"LTkiXYSA.zip|Wed Jan 03 22:16:12 CST 2018|3005526010|2.8 GB\",\n" +
            "\"Mzp3JaU6.zip|Fri Jan 05 14:54:05 CST 2018|113257319|108 MB\",\n" +
            "\"0aDML7Nx.zip|Tue Jan 02 21:58:40 CST 2018|1143436509|1.1 GB\",\n" +
            "\"wxyqfGvy.zip|Mon Jan 08 17:38:39 CST 2018|243633340|232 MB\",\n" +
            "\"tX1VH3cI.zip|Mon Jan 08 18:01:23 CST 2018|657069697|627 MB\",\n" +
            "\"2tDBZcla.zip|Mon Jan 08 19:21:40 CST 2018|1306871402|1.2 GB\",\n" +
            "\"1cVQle3A.zip|Tue Jan 09 10:27:36 CST 2018|1084142499|1.0 GB\",\n" +
            "\"NusN5uGL.zip|Tue Jan 09 11:40:16 CST 2018|2491020073|2.3 GB\",\n" +
            "\"GK2RasuB.zip|Fri Jan 12 18:12:47 CST 2018|334274404|319 MB\",\n" +
            "\"NQ6Eg8PJ.zip|Fri Jan 12 18:27:11 CST 2018|599370152|572 MB\",\n" +
            "\"G275xFsQ.zip|Tue Jan 16 14:53:07 CST 2018|923514906|881 MB\",\n" +
            "\"hRUPEubJ.zip|Fri Jan 26 23:59:48 CST 2018|635402676|606 MB\",\n" +
            "\"1vQveVXQ.zip|Tue Jan 30 11:11:00 CST 2018|101737336|97.0 MB\",\n" +
            "\"Dx9pbBq8.zip|Fri Feb 02 13:21:47 CST 2018|78172|76.3 KB\",\n" +
            "\"BiItLfim.zip|Tue Feb 06 08:28:26 CST 2018|2865683599|2.7 GB\",\n" +
            "\"aHh52mr1.zip|Thu Jan 04 14:46:17 CST 2018|145930527|139 MB\",\n" +
            "\"HUqeBX5j.zip|Tue Jan 02 22:07:52 CST 2018|2475265683|2.3 GB\",\n" +
            "\"DQiLhKOw.zip|Wed Jan 03 00:12:12 CST 2018|1163942174|1.1 GB\",\n" +
            "\"N0Zy5SXx.zip|Fri Jan 05 11:41:19 CST 2018|93129674|88.8 MB\",\n" +
            "\"ZMKzycEl.zip|Fri Feb 02 13:42:22 CST 2018|768209510|733 MB\",\n" +
            "\"mpVXiskl.zip|Tue Feb 06 12:37:54 CST 2018|428391015|409 MB\",\n" +
            "\"RoALAU2d.zip|Tue Aug 22 13:38:59 CST 2017|1216668260|1.1 GB\",\n" +
            "\"kWEwIWtn.zip|Tue Jan 02 22:15:27 CST 2018|1055960588|1007 MB\",\n" +
            "\"08KfP3kw.zip|Tue Jan 02 22:17:09 CST 2018|833898892|795 MB\",\n" +
            "\"0RHsC7vn.zip|Tue Jan 02 22:18:05 CST 2018|39363263|37.5 MB\",\n" +
            "\"DIzJmsm4.zip|Tue Jan 02 22:18:54 CST 2018|280696235|268 MB\",\n" +
            "\"yU7Lo0Cx.zip|Tue Jan 02 22:21:47 CST 2018|3252932891|3.0 GB\",\n" +
            "\"mVhcgWX6.zip|Tue Jan 02 23:56:19 CST 2018|122562041|117 MB\",\n" +
            "\"yhSVf8Dn.zip|Wed Jan 03 00:03:49 CST 2018|333816590|318 MB\",\n" +
            "\"KFw7X7Sw.zip|Tue Jan 02 14:03:40 CST 2018|1035839572|988 MB\",\n" +
            "\"SCvnVjvD.zip|Fri Jan 05 11:42:20 CST 2018|77656837|74.1 MB\",\n" +
            "\"vu5chTHA.zip|Tue Jan 02 22:25:32 CST 2018|79252781|75.6 MB\",\n" +
            "\"j4v4Qh5u.zip|Sun Feb 04 11:05:12 CST 2018|438328095|418 MB\",\n" +
            "\"szR1ZfbT.zip|Tue Feb 06 12:48:07 CST 2018|448448649|428 MB\",\n" +
            "\"YAVLIirt.zip|Tue Jan 02 22:31:09 CST 2018|2218927448|2.1 GB\",\n" +
            "\"3usTERwU.zip|Tue Jan 02 22:38:30 CST 2018|252619839|241 MB\",\n" +
            "\"Waa97hOO.zip|Tue Jan 02 22:37:23 CST 2018|879268531|839 MB\",\n" +
            "\"UnAfnhb1.zip|Tue Jan 02 22:39:57 CST 2018|1420516932|1.3 GB\",\n" +
            "\"YEzcgZOj.zip|Tue Jan 02 22:34:44 CST 2018|1890655321|1.8 GB\",\n" +
            "\"GHzxj5NA.zip|Tue Jan 02 22:42:05 CST 2018|31866802|30.4 MB\",\n" +
            "\"hhPdNm3x.zip|Tue Jan 02 22:42:36 CST 2018|13259617|12.6 MB\",\n" +
            "\"YBgbH171.zip|Tue Jan 02 22:42:20 CST 2018|116649430|111 MB\",\n" +
            "\"TlrR7z5I.zip|Tue Jan 02 22:41:39 CST 2018|283428466|270 MB\",\n" +
            "\"NsAdSUVX.zip|Tue Jan 02 22:46:05 CST 2018|1079471681|1.0 GB\",\n" +
            "\"MzQxnGhk.zip|Tue Jan 02 22:43:51 CST 2018|1400928063|1.3 GB\",\n" +
            "\"PXqBPOsT.zip|Fri Jan 12 18:54:38 CST 2018|1212585348|1.1 GB\",\n" +
            "\"Cxh94h8Y.zip|Wed Jan 17 15:23:01 CST 2018|829568|810 KB\",\n" +
            "\"EO3TCLAE.zip|Thu Feb 01 11:07:21 CST 2018|176413000|168 MB\",\n" +
            "\"VKUwF2PC.zip|Mon Feb 05 14:47:04 CST 2018|625521882|597 MB\",\n" +
            "\"SyJwKteO.zip|Tue Feb 06 12:57:25 CST 2018|698088170|666 MB\",\n" +
            "\"hqSeVoGN.zip|Tue Jan 02 22:53:14 CST 2018|1264775803|1.2 GB\",\n" +
            "\"vfZQy7XO.zip|Tue Jan 02 15:17:57 CST 2018|599227939|571 MB\",\n" +
            "\"xg3Bogn5.zip|Fri Jan 05 11:55:03 CST 2018|205264972|196 MB\",\n" +
            "\"R2KeTrfD.zip|Tue Jan 02 23:08:21 CST 2018|196155117|187 MB\",\n" +
            "\"JCMsED5M.zip|Tue Jan 02 23:19:29 CST 2018|2009314313|1.9 GB\",\n" +
            "\"o51aiR7K.zip|Tue Jan 02 23:52:07 CST 2018|41139995|39.2 MB\",\n" +
            "\"jkTMXSwA.zip|Tue Jan 02 23:33:45 CST 2018|519907028|496 MB\",\n" +
            "\"7ATiFE5u.zip|Tue Jan 02 23:48:13 CST 2018|692962425|661 MB\",\n" +
            "\"16jxV27k.zip|Fri Jan 05 15:04:13 CST 2018|548382610|523 MB\",\n" +
            "\"42uT78tw.zip|Fri Jan 05 15:23:01 CST 2018|319938917|305 MB\",\n" +
            "\"toV8w15J.zip|Tue Jan 09 10:46:07 CST 2018|413581229|394 MB\",\n" +
            "\"0FzYe9MX.zip|Mon Jan 08 19:33:14 CST 2018|318002561|303 MB\",\n" +
            "\"Ce5U9unO.zip|Mon Jan 08 19:51:20 CST 2018|546055645|521 MB\",\n" +
            "\"cw01qh6O.zip|Mon Jan 08 18:50:11 CST 2018|251390247|240 MB\",\n" +
            "\"ebTKEr7A.zip|Wed Jan 17 16:05:25 CST 2018|525119045|501 MB\",\n" +
            "\"HLUuVjcJ.zip|Wed Jan 17 16:06:02 CST 2018|705259907|673 MB\",\n" +
            "\"9hO22fbx.zip|Wed Jan 17 16:12:39 CST 2018|633018361|604 MB\",\n" +
            "\"BssYc2y3.zip|Wed Jan 17 16:24:15 CST 2018|984670416|939 MB\",\n" +
            "\"keD75vdh.zip|Wed Jan 17 16:44:44 CST 2018|1863885820|1.7 GB\",\n" +
            "\"mqdM1mLC.zip|Thu Jan 18 11:34:46 CST 2018|64716506|61.7 MB\",\n" +
            "\"OJOT5FuN.zip|Thu Jan 18 11:36:53 CST 2018|77473259|73.9 MB\",\n" +
            "\"dBzmAXyS.zip|Fri Jan 19 21:35:32 CST 2018|385830037|368 MB\",\n" +
            "\"6J3QRCoS.zip|Mon Jan 22 17:45:20 CST 2018|285799418|273 MB\",\n" +
            "\"iZYV6dzC.zip|Mon Jan 22 23:25:45 CST 2018|297463310|284 MB\",\n" +
            "\"J3IVYrSP.zip|Tue Jan 23 23:13:45 CST 2018|518523381|495 MB\",\n" +
            "\"11Mt4OmV.zip|Fri Jan 26 10:37:19 CST 2018|1121919|1.1 MB\",\n" +
            "\"xf1dPxmH.zip|Fri Jan 26 10:39:19 CST 2018|28158724|26.9 MB\",\n" +
            "\"UcC7dnAb.zip|Fri Jan 26 10:45:33 CST 2018|77731105|74.1 MB\",\n" +
            "\"d9AB8kkV.zip|Sat Jan 27 00:31:44 CST 2018|43613314|41.6 MB\",\n" +
            "\"QkVTv76Z.zip|Thu Feb 01 15:20:45 CST 2018|1400869540|1.3 GB\",\n" +
            "\"Diy3WbVA.zip|Mon Feb 05 16:13:45 CST 2018|452172601|431 MB\"\n" +
            "]";

    private static  Date now = new Date();
    public static void main(String[] args) {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(new Date());
        calendarStart.set(calendarStart.get(Calendar.YEAR),
                calendarStart.get(Calendar.MONTH),
                calendarStart.get(Calendar.DATE),
                0, 0, 0);
        calendarStart.add(Calendar.DAY_OF_MONTH, -1);
        String nowStr = DateUtil.formatByPattern(calendarStart.getTime(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("nowStr is [ " + nowStr + "'");
        Gson gson = new Gson();
        Map<Object,Object> map = gson.fromJson(expireCampaignIds, Map.class);
        System.out.println(map);

        List<String> clearCampaignIds = new ArrayList<>();
        for (Map.Entry<Object,Object> entry : map.entrySet()){
            Object startAndEndDate =  entry.getValue();
            List<String> list = (List<String>) startAndEndDate;
            String endDateStr = list.get(1);
            if (endDateStr.compareToIgnoreCase(nowStr) <= 0){// 投放结束时间到了，就该清楚
                clearCampaignIds.add((String) entry.getKey());
            }
        }

        System.out.println("clear campaignIds is1 " + clearCampaignIds + ", size is1 [ " + clearCampaignIds.size());
        muchCapacity(clearCampaignIds);
    }

    public  static void muchCapacity(List<String> expireCampaignIds){
        Type type = new TypeToken<List<String>>(){}.getType();
        List<String> campaignAndSize = new Gson().fromJson(campaignIdsMuch,type);
        Map<String,Double> map = new HashMap<>();
        for (String str : campaignAndSize){
            String[] strs = str.split("\\|");
            String size = strs[3];
            Double campaignSize = 0d;
            if (size.contains("MB")){
                campaignSize = Double.parseDouble(size.split(" MB")[0]);
            }else if (size.contains("GB")){
                campaignSize = Double.parseDouble(size.split(" GB")[0]) * 1000;
            }else {// MB以下的不进行计算
                continue;
            }
            map.put(strs[0].split("\\.")[0],campaignSize);
        }
        System.out.println(map);

        double sumSize = 0;
        for (String str : expireCampaignIds){
            if (map.containsKey(str)){
                sumSize = sumSize + map.get(str);
            }
        }
        System.out.println("sumSize is [ " + sumSize / 1000 + " ] GB");
    }
}
