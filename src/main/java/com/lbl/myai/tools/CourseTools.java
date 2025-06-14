package com.lbl.myai.tools;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbl.myai.entity.po.Course;
import com.lbl.myai.entity.po.CourseReservation;
import com.lbl.myai.entity.po.School;
import com.lbl.myai.entity.query.CourseQuery;
import com.lbl.myai.service.ICourseReservationService;
import com.lbl.myai.service.ICourseService;
import com.lbl.myai.service.ISchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseTools {

    private final ICourseService courseService;
    private final ISchoolService schoolService;
    private final ICourseReservationService courseReservationService;

    @Tool(description = "根据条件查询课程")
    public List<Course> queryCourse(@ToolParam(description = "查询条件") CourseQuery courseQuery) {
        if (courseQuery == null) {
            return List.of();
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(courseQuery.getType() != null, "type", courseQuery.getType())
                .le(courseQuery.getEdu() != null, "edu", courseQuery.getEdu());
        if (courseQuery.getSorts() != null && courseQuery.getSorts().size() > 0) {
            for (CourseQuery.Sort sort : courseQuery.getSorts()) {
                if (sort.getAsc()) {
                    queryWrapper.orderByAsc(sort.getField());
                } else {
                    queryWrapper.orderByDesc(sort.getField());
                }
            }
        }
        return courseService.list(queryWrapper);
    }

    @Tool(description = "查询所有校区")
    public List<School> querySchool() {
        return schoolService.list();
    }

    @Tool(description = "生成预约单，返回预约单号")
    public Integer createCourseReservation(
            @ToolParam(description = "预约课程") String course,
            @ToolParam(description = "预约校区") String school,
            @ToolParam(description = "学生姓名") String studentName,
            @ToolParam(description = "联系电话") String contactInfo,
            @ToolParam(description = "备注",required = false) String remark) {
        CourseReservation courseReservation = new CourseReservation();
        courseReservation.setCourse(course);
        courseReservation.setSchool(school);
        courseReservation.setStudentName(studentName);
        courseReservation.setContactInfo(contactInfo);
        courseReservation.setRemark(remark);
        courseReservationService.save(courseReservation);
        return courseReservation.getId();
    }

}
