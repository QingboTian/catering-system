package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.enums.RoleEnum;
import cn.tianqb.utils.WebHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/18 19:01
 */
@RestController
@Slf4j
@RequestMapping("/api/menu")
public class MenuController {

    @GetMapping()
    public WebResult<List<MenuItem>> menu() {
        Integer roleId = WebHelper.getRoleId();
        if (RoleEnum.ADMINISTRATOR.getCode().equals(roleId)) {
            return WebResult.ok(buildAdminMenu());
        } else if (RoleEnum.USER.getCode().equals(roleId)) {
            return WebResult.ok(buildUserMenu());
        } else {
            return WebResult.ok(new ArrayList<>());
        }
    }

    private List<MenuItem> buildUserMenu() {
        List<MenuItem> list = new ArrayList<>();
        MenuItem index = new MenuItem();
        index.setIcon("el-icon-lx-home");
        index.setIndex("index");
        index.setTitle("首页");
        list.add(index);
        MenuItem order = new MenuItem();
        order.setIcon("el-icon-lx-calendar");
        order.setIndex("order");
        order.setTitle("订单管理");
        list.add(order);
        MenuItem room = new MenuItem();
        room.setIcon("el-icon-rank");
        room.setIndex("room");
        room.setTitle("包间列表");
        list.add(room);
        MenuItem appointment = new MenuItem();
        appointment.setIcon("el-icon-lx-calendar");
        appointment.setIndex("appointment");
        appointment.setTitle("我的预约");
        list.add(appointment);
        MenuItem onlineOrder = new MenuItem();
        onlineOrder.setIcon("el-icon-rank");
        onlineOrder.setIndex("onlineOrder");
        onlineOrder.setTitle("在线下单");
        list.add(onlineOrder);
        return list;
    }

    private List<MenuItem> buildAdminMenu() {
        List<MenuItem> list = new ArrayList<>();
        MenuItem index = new MenuItem();
        index.setIcon("el-icon-lx-home");
        index.setIndex("index");
        index.setTitle("首页");
        list.add(index);
        MenuItem category = new MenuItem();
        category.setIcon("el-icon-lx-cascades");
        category.setIndex("category");
        category.setTitle("类目管理");
        list.add(category);
        MenuItem dishes = new MenuItem();
        dishes.setIcon("el-icon-lx-copy");
        dishes.setIndex("dishes");
        dishes.setTitle("菜品管理");
        list.add(dishes);
        MenuItem order = new MenuItem();
        order.setIcon("el-icon-lx-calendar");
        order.setIndex("order");
        order.setTitle("订单管理");
        list.add(order);
        MenuItem user = new MenuItem();
        user.setIcon("el-icon-lx-emoji");
        user.setIndex("user");
        user.setTitle("用户管理");
        list.add(user);
        MenuItem room = new MenuItem();
        room.setIcon("el-icon-rank");
        room.setIndex("room");
        room.setTitle("包间管理");
        list.add(room);
        MenuItem appointment = new MenuItem();
        appointment.setIcon("el-icon-rank");
        appointment.setIndex("appointment");
        appointment.setTitle("预约管理");
        list.add(appointment);
        MenuItem onlineOrder = new MenuItem();
        onlineOrder.setIcon("el-icon-rank");
        onlineOrder.setIndex("onlineOrder");
        onlineOrder.setTitle("在线下单");
        list.add(onlineOrder);
        return list;
    }

    static class MenuItem {
        private String icon;
        private String index;
        private String title;
        private List<MenuItem> subs;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<MenuItem> getSubs() {
            return subs;
        }

        public void setSubs(List<MenuItem> subs) {
            this.subs = subs;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "icon='" + icon + '\'' +
                    ", index='" + index + '\'' +
                    ", title='" + title + '\'' +
                    ", subs=" + subs +
                    '}';
        }
    }
}
