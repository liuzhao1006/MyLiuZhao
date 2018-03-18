package com.sx.trans.company.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sx.trans.R;
import com.sx.trans.company.bean.CommpanyAccidentBean;
import com.sx.trans.company.bean.CommpanyCommonBean;
import com.sx.trans.company.bean.CommpanyMeetManageInfoBean;
import com.sx.trans.company.bean.CommpanyPoliciesAndObjectivesBean;
import com.sx.trans.company.bean.CommpanySafeInvestmentBean;
import com.sx.trans.company.bean.CommpanySecurityAdministrationBean;
import com.sx.trans.company.bean.CommpanySpecialactivitiesBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class CommonAdapter extends BaseAdapter {

    private Context context;


    private int i;
    private List<CommpanyCommonBean> data;
    private List<CommpanyMeetManageInfoBean> localLawBeans;
    private List<CommpanyPoliciesAndObjectivesBean> PoliciesAndObjectivesBeans;
    private List<CommpanySecurityAdministrationBean> SecurityAdministrationBeans;
    private List<CommpanySafeInvestmentBean> safeInvestmentBeans;
    private List<CommpanyCommonBean> emergencyBeans;

    private List<CommpanySpecialactivitiesBean> SpecialactivitiesBeans;
    private List<CommpanyAccidentBean> AccidentBeans;


    public CommonAdapter(Context context, int i, List<CommpanyCommonBean> data, List<CommpanyMeetManageInfoBean> localLawBeans
            , List<CommpanyPoliciesAndObjectivesBean> PoliciesAndObjectivesBeans, List<CommpanySecurityAdministrationBean> SecurityAdministrationBeans,
                         List<CommpanySafeInvestmentBean> safeInvestmentBeans, List<CommpanyCommonBean> emergencyBeans, List<CommpanySpecialactivitiesBean> SpecialactivitiesBeans
            , List<CommpanyAccidentBean> AccidentBeans) {
        this.context = context;
        this.i = i;
        this.data = data;
        this.localLawBeans = localLawBeans;
        this.PoliciesAndObjectivesBeans = PoliciesAndObjectivesBeans;
        this.SecurityAdministrationBeans = SecurityAdministrationBeans;
        this.safeInvestmentBeans = safeInvestmentBeans;
        this.emergencyBeans = emergencyBeans;
        this.SpecialactivitiesBeans = SpecialactivitiesBeans;
        this.AccidentBeans = AccidentBeans;

    }


    @Override
    public int getCount() {
        if (i == 2) {

            return emergencyBeans.size();

        } else if (i == 4) {

            return AccidentBeans.size();

        } else if (i == 7) {

            return localLawBeans.size();

        } else if (i == 9) {

            return safeInvestmentBeans.size();

        } else if (i == 11) {

            return SecurityAdministrationBeans.size();

        } else if (i == 12) {

            return PoliciesAndObjectivesBeans.size();

        } else if (i == 10) {

            return SpecialactivitiesBeans.size();
        }

        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_exception_message, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type); // 标题
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (i == 2) {//安全演练

            holder.tv_name.setText(emergencyBeans.get(position).getTitle() + "");
            holder.tv_state.setText(emergencyBeans.get(position).getResult() + "");
            holder.tv_date.setText(emergencyBeans.get(position).getDate() + "");
            holder.tv_type.setText(emergencyBeans.get(position).getGrade() + "");


        } else if (i == 7) {//会议

            holder.tv_name.setText(localLawBeans.get(position).getTitle());
            holder.tv_state.setText(format.format(localLawBeans.get(position).getCreateDate()) + "");
            holder.tv_date.setText(localLawBeans.get(position).getContent());
            holder.tv_type.setText(localLawBeans.get(position).getCreateUser());


        } else if (i == 9) {//安全投入


            holder.tv_name.setText(safeInvestmentBeans.get(position).getYear());
            holder.tv_state.setText(safeInvestmentBeans.get(position).getRatio() + "%");
            holder.tv_date.setText(safeInvestmentBeans.get(position).getSurplusMoney() + "元");
            holder.tv_state.setText(safeInvestmentBeans.get(position).getRatio() * 100 + "%");
            holder.tv_date.setText(safeInvestmentBeans.get(position).getSurplusMoney() + "元");
            holder.tv_type.setText(format.format(safeInvestmentBeans.get(position).getCreateDate()) + "");


        } else if (i == 11) {//制度规范

            holder.tv_name.setText(SecurityAdministrationBeans.get(position).getInstitutionName());
            holder.tv_state.setText(format.format(SecurityAdministrationBeans.get(position).getExecuteDate()) + "");
            holder.tv_date.setText(SecurityAdministrationBeans.get(position).getCreateUser());
            if (SecurityAdministrationBeans.get(position).getIsUse() == true) {
                holder.tv_type.setText("使用中");
            } else {

                holder.tv_type.setText("未使用");
            }


        } else if (i == 12) {//安全目标

            holder.tv_name.setText(PoliciesAndObjectivesBeans.get(position).getPointName());
            holder.tv_state.setText(format.format(PoliciesAndObjectivesBeans.get(position).getFormulateDate()));
            holder.tv_date.setText("");
            if (PoliciesAndObjectivesBeans.get(position).getIsUse() == true) {
                holder.tv_type.setText("使用中");
            } else {

                holder.tv_type.setText("未使用");
            }


        } else if (i == 10) {
            holder.tv_name.setText(SpecialactivitiesBeans.get(position).getTitle());
            holder.tv_state.setText(format.format(SpecialactivitiesBeans.get(position).getCreateDate()));
            holder.tv_date.setText(SpecialactivitiesBeans.get(position).getPurpose());
            holder.tv_type.setText(SpecialactivitiesBeans.get(position).getCreateUser());

        } else if (i == 4) {

            holder.tv_name.setText(AccidentBeans.get(position).getAccidentname());
            holder.tv_state.setText(format.format(AccidentBeans.get(position).getAccidentdate()));
            holder.tv_date.setText(AccidentBeans.get(position).getAccidentsurvey());
            holder.tv_type.setText(AccidentBeans.get(position).getCreateUser());

        } else {
            holder.tv_name.setText(data.get(position).getTitle() + "");
            holder.tv_state.setText(data.get(position).getResult() + "");
            holder.tv_date.setText(data.get(position).getDate() + "");
            holder.tv_type.setText(data.get(position).getGrade() + "");
        }


        return convertView;
    }

    static class ViewHolder {

        TextView tv_name;
        TextView tv_state;
        TextView tv_date;

        TextView tv_type;
    }
}
