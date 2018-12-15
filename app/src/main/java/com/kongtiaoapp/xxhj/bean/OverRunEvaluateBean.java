package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/9/16.
 */

public class OverRunEvaluateBean extends RBResponse {


    /**
     * code : 40000
     * resobj : {"WtEval":{"Temp":{"Compared":["实际","国标","实际","湿球温度"],"Data":[{"Value":[0,7,24.05,14],"sign":["冷冻温度","冷却温度"]}],"Indicator":2,"MaxY":24.05,"MinY":0},"diffTemp":{"Compared":["实际","国标","实际","国标"],"Data":[{"Value":[2.12,5,1.85,5],"sign":["冷却温差","冷冻温差"]}],"Indicator":2,"MaxY":5,"MinY":1.85,"NowTime":1505887694933},"desc":"4.冷却水供回水温差为2.12℃，冷却水量偏多，其中冷却供水温度为24.05℃，冷却水温控制合理但仍有潜力可挖，需适当调整风机。冷冻水供回水温差为1.85℃ ，冷冻水量偏多，其中冷冻水供水温度为0.0℃，冷冻水供水温度偏低，适当提高水温以提高冷机效率。"},"CopEval":{"desc":"2.制冷系统SCOP为1.55（国标为：4.1），冷机COP为3.58（国标为：5.5），运行冷机的负载率太小,制冷系统运行状况差，冷机运行状况差;","fefrigeEFF":{"Compared":["实际","国标","实际","国标"],"Data":[{"Value":[1.55,4.1,3.58,5.5],"sign":["SCOP","COP"]}],"Indicator":2,"MaxY":5.5,"MinY":1.55,"NowTime":1505887694929},"scopScore":{"Data":[{"Name":"scopScore","Text":"scopScore"}],"Limit":[60,75,85],"NowTime":1505887694929,"Table":["差","中","良","优"],"score":22.682926829268293},"copScore":{"Data":[{"Name":"copScore","Text":"copScore"}],"Limit":[60,75,85],"NowTime":1505887694929,"Table":["差","中","良","优"],"score":39.054545454545455}},"PumpEval":{"Status":{"Data":[{"Name":"Status","Text":"运行状态"}],"Limit":[60,75,85],"NowTime":1505887694932,"Table":["差","中","良","优"],"score":94.184},"desc":"3.制冷系统耗电输冷比为0.0，输送单位冷量冷冻水泵耗电优，其中冷冻水泵效率偏小，冷却水泵效率偏小；"},"EcEval":{"RunSC":{"Compared":["今日时长","昨日时长"],"Data":[{"Name":"RunSC","Text":"运行时长","Value":[5.63,10.27]}],"MaxY":10.27,"MinY":5.63,"NowTime":1505887694928},"RunEC":{"Compared":["今日能耗","昨日能耗"],"Data":[{"Name":"RunEC","Text":"运行耗电","Value":[3386.98,3609.27]}],"MaxY":3609.27,"MinY":3386.98,"NowTime":1505887694928},"desc":"1.今日温度28.0℃，比昨天低2.0℃  今日开机运行时长5.63小时，比昨日运行时长低4.64小时;"}}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * WtEval : {"Temp":{"Compared":["实际","国标","实际","湿球温度"],"Data":[{"Value":[0,7,24.05,14],"sign":["冷冻温度","冷却温度"]}],"Indicator":2,"MaxY":24.05,"MinY":0},"diffTemp":{"Compared":["实际","国标","实际","国标"],"Data":[{"Value":[2.12,5,1.85,5],"sign":["冷却温差","冷冻温差"]}],"Indicator":2,"MaxY":5,"MinY":1.85,"NowTime":1505887694933},"desc":"4.冷却水供回水温差为2.12℃，冷却水量偏多，其中冷却供水温度为24.05℃，冷却水温控制合理但仍有潜力可挖，需适当调整风机。冷冻水供回水温差为1.85℃ ，冷冻水量偏多，其中冷冻水供水温度为0.0℃，冷冻水供水温度偏低，适当提高水温以提高冷机效率。"}
         * CopEval : {"desc":"2.制冷系统SCOP为1.55（国标为：4.1），冷机COP为3.58（国标为：5.5），运行冷机的负载率太小,制冷系统运行状况差，冷机运行状况差;","fefrigeEFF":{"Compared":["实际","国标","实际","国标"],"Data":[{"Value":[1.55,4.1,3.58,5.5],"sign":["SCOP","COP"]}],"Indicator":2,"MaxY":5.5,"MinY":1.55,"NowTime":1505887694929},"scopScore":{"Data":[{"Name":"scopScore","Text":"scopScore"}],"Limit":[60,75,85],"NowTime":1505887694929,"Table":["差","中","良","优"],"score":22.682926829268293},"copScore":{"Data":[{"Name":"copScore","Text":"copScore"}],"Limit":[60,75,85],"NowTime":1505887694929,"Table":["差","中","良","优"],"score":39.054545454545455}}
         * PumpEval : {"Status":{"Data":[{"Name":"Status","Text":"运行状态"}],"Limit":[60,75,85],"NowTime":1505887694932,"Table":["差","中","良","优"],"score":94.184},"desc":"3.制冷系统耗电输冷比为0.0，输送单位冷量冷冻水泵耗电优，其中冷冻水泵效率偏小，冷却水泵效率偏小；"}
         * EcEval : {"RunSC":{"Compared":["今日时长","昨日时长"],"Data":[{"Name":"RunSC","Text":"运行时长","Value":[5.63,10.27]}],"MaxY":10.27,"MinY":5.63,"NowTime":1505887694928},"RunEC":{"Compared":["今日能耗","昨日能耗"],"Data":[{"Name":"RunEC","Text":"运行耗电","Value":[3386.98,3609.27]}],"MaxY":3609.27,"MinY":3386.98,"NowTime":1505887694928},"desc":"1.今日温度28.0℃，比昨天低2.0℃  今日开机运行时长5.63小时，比昨日运行时长低4.64小时;"}
         */

        private WtEvalBean WtEval;
        private CopEvalBean CopEval;
        private PumpEvalBean PumpEval;
        private EcEvalBean EcEval;

        public WtEvalBean getWtEval() {
            return WtEval;
        }

        public void setWtEval(WtEvalBean WtEval) {
            this.WtEval = WtEval;
        }

        public CopEvalBean getCopEval() {
            return CopEval;
        }

        public void setCopEval(CopEvalBean CopEval) {
            this.CopEval = CopEval;
        }

        public PumpEvalBean getPumpEval() {
            return PumpEval;
        }

        public void setPumpEval(PumpEvalBean PumpEval) {
            this.PumpEval = PumpEval;
        }

        public EcEvalBean getEcEval() {
            return EcEval;
        }

        public void setEcEval(EcEvalBean EcEval) {
            this.EcEval = EcEval;
        }

        public static class WtEvalBean {
            /**
             * Temp : {"Compared":["实际","国标","实际","湿球温度"],"Data":[{"Value":[0,7,24.05,14],"sign":["冷冻温度","冷却温度"]}],"Indicator":2,"MaxY":24.05,"MinY":0}
             * diffTemp : {"Compared":["实际","国标","实际","国标"],"Data":[{"Value":[2.12,5,1.85,5],"sign":["冷却温差","冷冻温差"]}],"Indicator":2,"MaxY":5,"MinY":1.85,"NowTime":1505887694933}
             * desc : 4.冷却水供回水温差为2.12℃，冷却水量偏多，其中冷却供水温度为24.05℃，冷却水温控制合理但仍有潜力可挖，需适当调整风机。冷冻水供回水温差为1.85℃ ，冷冻水量偏多，其中冷冻水供水温度为0.0℃，冷冻水供水温度偏低，适当提高水温以提高冷机效率。
             */

            private TempBean Temp;
            private DiffTempBean diffTemp;
            private String desc;

            public TempBean getTemp() {
                return Temp;
            }

            public void setTemp(TempBean Temp) {
                this.Temp = Temp;
            }

            public DiffTempBean getDiffTemp() {
                return diffTemp;
            }

            public void setDiffTemp(DiffTempBean diffTemp) {
                this.diffTemp = diffTemp;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public static class TempBean {
                /**
                 * Compared : ["实际","国标","实际","湿球温度"]
                 * Data : [{"Value":[0,7,24.05,14],"sign":["冷冻温度","冷却温度"]}]
                 * Indicator : 2
                 * MaxY : 24.05
                 * MinY : 0
                 */

                private int Indicator;
                private double MaxY;
                private double MinY;
                private List<String> Compared;
                private List<DataBean> Data;

                public int getIndicator() {
                    return Indicator;
                }

                public void setIndicator(int Indicator) {
                    this.Indicator = Indicator;
                }

                public double getMaxY() {
                    return MaxY;
                }

                public void setMaxY(double MaxY) {
                    this.MaxY = MaxY;
                }

                public double getMinY() {
                    return MinY;
                }

                public void setMinY(double MinY) {
                    this.MinY = MinY;
                }

                public List<String> getCompared() {
                    return Compared;
                }

                public void setCompared(List<String> Compared) {
                    this.Compared = Compared;
                }

                public List<DataBean> getData() {
                    return Data;
                }

                public void setData(List<DataBean> Data) {
                    this.Data = Data;
                }

                public static class DataBean {
                    private double[] Value;
                    private List<String> sign;

                    public double[] getValue() {
                        return Value;
                    }

                    public void setValue(double[] Value) {
                        this.Value = Value;
                    }

                    public List<String> getSign() {
                        return sign;
                    }

                    public void setSign(List<String> sign) {
                        this.sign = sign;
                    }
                }
            }

            public static class DiffTempBean {
                /**
                 * Compared : ["实际","国标","实际","国标"]
                 * Data : [{"Value":[2.12,5,1.85,5],"sign":["冷却温差","冷冻温差"]}]
                 * Indicator : 2
                 * MaxY : 5
                 * MinY : 1.85
                 * NowTime : 1505887694933
                 */

                private int Indicator;
                private double MaxY;
                private double MinY;
                private long NowTime;
                private List<String> Compared;
                private List<DataBeanX> Data;

                public int getIndicator() {
                    return Indicator;
                }

                public void setIndicator(int Indicator) {
                    this.Indicator = Indicator;
                }

                public double getMaxY() {
                    return MaxY;
                }

                public void setMaxY(double MaxY) {
                    this.MaxY = MaxY;
                }

                public double getMinY() {
                    return MinY;
                }

                public void setMinY(double MinY) {
                    this.MinY = MinY;
                }

                public long getNowTime() {
                    return NowTime;
                }

                public void setNowTime(long NowTime) {
                    this.NowTime = NowTime;
                }

                public List<String> getCompared() {
                    return Compared;
                }

                public void setCompared(List<String> Compared) {
                    this.Compared = Compared;
                }

                public List<DataBeanX> getData() {
                    return Data;
                }

                public void setData(List<DataBeanX> Data) {
                    this.Data = Data;
                }

                public static class DataBeanX {
                    private double[] Value;
                    private List<String> sign;

                    public double[] getValue() {
                        return Value;
                    }

                    public void setValue(double[] Value) {
                        this.Value = Value;
                    }

                    public List<String> getSign() {
                        return sign;
                    }

                    public void setSign(List<String> sign) {
                        this.sign = sign;
                    }
                }
            }
        }

        public static class CopEvalBean {
            /**
             * desc : 2.制冷系统SCOP为1.55（国标为：4.1），冷机COP为3.58（国标为：5.5），运行冷机的负载率太小,制冷系统运行状况差，冷机运行状况差;
             * fefrigeEFF : {"Compared":["实际","国标","实际","国标"],"Data":[{"Value":[1.55,4.1,3.58,5.5],"sign":["SCOP","COP"]}],"Indicator":2,"MaxY":5.5,"MinY":1.55,"NowTime":1505887694929}
             * scopScore : {"Data":[{"Name":"scopScore","Text":"scopScore"}],"Limit":[60,75,85],"NowTime":1505887694929,"Table":["差","中","良","优"],"score":22.682926829268293}
             * copScore : {"Data":[{"Name":"copScore","Text":"copScore"}],"Limit":[60,75,85],"NowTime":1505887694929,"Table":["差","中","良","优"],"score":39.054545454545455}
             */

            private String desc;
            private FefrigeEFFBean fefrigeEFF;
            private ScopScoreBean scopScore;
            private CopScoreBean copScore;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public FefrigeEFFBean getFefrigeEFF() {
                return fefrigeEFF;
            }

            public void setFefrigeEFF(FefrigeEFFBean fefrigeEFF) {
                this.fefrigeEFF = fefrigeEFF;
            }

            public ScopScoreBean getScopScore() {
                return scopScore;
            }

            public void setScopScore(ScopScoreBean scopScore) {
                this.scopScore = scopScore;
            }

            public CopScoreBean getCopScore() {
                return copScore;
            }

            public void setCopScore(CopScoreBean copScore) {
                this.copScore = copScore;
            }

            public static class FefrigeEFFBean {
                /**
                 * Compared : ["实际","国标","实际","国标"]
                 * Data : [{"Value":[1.55,4.1,3.58,5.5],"sign":["SCOP","COP"]}]
                 * Indicator : 2
                 * MaxY : 5.5
                 * MinY : 1.55
                 * NowTime : 1505887694929
                 */

                private int Indicator;
                private double MaxY;
                private double MinY;
                private long NowTime;
                private List<String> Compared;
                private List<DataBeanXX> Data;

                public int getIndicator() {
                    return Indicator;
                }

                public void setIndicator(int Indicator) {
                    this.Indicator = Indicator;
                }

                public double getMaxY() {
                    return MaxY;
                }

                public void setMaxY(double MaxY) {
                    this.MaxY = MaxY;
                }

                public double getMinY() {
                    return MinY;
                }

                public void setMinY(double MinY) {
                    this.MinY = MinY;
                }

                public long getNowTime() {
                    return NowTime;
                }

                public void setNowTime(long NowTime) {
                    this.NowTime = NowTime;
                }

                public List<String> getCompared() {
                    return Compared;
                }

                public void setCompared(List<String> Compared) {
                    this.Compared = Compared;
                }

                public List<DataBeanXX> getData() {
                    return Data;
                }

                public void setData(List<DataBeanXX> Data) {
                    this.Data = Data;
                }

                public static class DataBeanXX {
                    private double[] Value;
                    private List<String> sign;

                    public double[] getValue() {
                        return Value;
                    }

                    public void setValue(double[] Value) {
                        this.Value = Value;
                    }

                    public List<String> getSign() {
                        return sign;
                    }

                    public void setSign(List<String> sign) {
                        this.sign = sign;
                    }
                }
            }

            public static class ScopScoreBean {
                /**
                 * Data : [{"Name":"scopScore","Text":"scopScore"}]
                 * Limit : [60,75,85]
                 * NowTime : 1505887694929
                 * Table : ["差","中","良","优"]
                 * score : 22.682926829268293
                 */

                private long NowTime;
                private double score;
                private List<DataBeanXXX> Data;
                private List<Float> Limit;
                private List<String> Table;

                public long getNowTime() {
                    return NowTime;
                }

                public void setNowTime(long NowTime) {
                    this.NowTime = NowTime;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public List<DataBeanXXX> getData() {
                    return Data;
                }

                public void setData(List<DataBeanXXX> Data) {
                    this.Data = Data;
                }

                public List<Float> getLimit() {
                    return Limit;
                }

                public void setLimit(List<Float> Limit) {
                    this.Limit = Limit;
                }

                public List<String> getTable() {
                    return Table;
                }

                public void setTable(List<String> Table) {
                    this.Table = Table;
                }

                public static class DataBeanXXX {
                    /**
                     * Name : scopScore
                     * Text : scopScore
                     */

                    private String Name;
                    private String Text;

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
                    }

                    public String getText() {
                        return Text;
                    }

                    public void setText(String Text) {
                        this.Text = Text;
                    }
                }
            }

            public static class CopScoreBean {
                /**
                 * Data : [{"Name":"copScore","Text":"copScore"}]
                 * Limit : [60,75,85]
                 * NowTime : 1505887694929
                 * Table : ["差","中","良","优"]
                 * score : 39.054545454545455
                 */

                private long NowTime;
                private double score;
                private List<DataBeanXXXX> Data;
                private List<Float> Limit;
                private List<String> Table;

                public long getNowTime() {
                    return NowTime;
                }

                public void setNowTime(long NowTime) {
                    this.NowTime = NowTime;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public List<DataBeanXXXX> getData() {
                    return Data;
                }

                public void setData(List<DataBeanXXXX> Data) {
                    this.Data = Data;
                }

                public List<Float> getLimit() {
                    return Limit;
                }

                public void setLimit(List<Float> Limit) {
                    this.Limit = Limit;
                }

                public List<String> getTable() {
                    return Table;
                }

                public void setTable(List<String> Table) {
                    this.Table = Table;
                }

                public static class DataBeanXXXX {
                    /**
                     * Name : copScore
                     * Text : copScore
                     */

                    private String Name;
                    private String Text;

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
                    }

                    public String getText() {
                        return Text;
                    }

                    public void setText(String Text) {
                        this.Text = Text;
                    }
                }
            }
        }

        public static class PumpEvalBean {
            /**
             * Status : {"Data":[{"Name":"Status","Text":"运行状态"}],"Limit":[60,75,85],"NowTime":1505887694932,"Table":["差","中","良","优"],"score":94.184}
             * desc : 3.制冷系统耗电输冷比为0.0，输送单位冷量冷冻水泵耗电优，其中冷冻水泵效率偏小，冷却水泵效率偏小；
             */

            private StatusBean Status;
            private String desc;

            public StatusBean getStatus() {
                return Status;
            }

            public void setStatus(StatusBean Status) {
                this.Status = Status;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public static class StatusBean {
                /**
                 * Data : [{"Name":"Status","Text":"运行状态"}]
                 * Limit : [60,75,85]
                 * NowTime : 1505887694932
                 * Table : ["差","中","良","优"]
                 * score : 94.184
                 */

                private long NowTime;
                private double score;
                private List<DataBeanXXXXX> Data;
                private List<Float> Limit;
                private List<String> Table;

                public long getNowTime() {
                    return NowTime;
                }

                public void setNowTime(long NowTime) {
                    this.NowTime = NowTime;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public List<DataBeanXXXXX> getData() {
                    return Data;
                }

                public void setData(List<DataBeanXXXXX> Data) {
                    this.Data = Data;
                }

                public List<Float> getLimit() {
                    return Limit;
                }

                public void setLimit(List<Float> Limit) {
                    this.Limit = Limit;
                }

                public List<String> getTable() {
                    return Table;
                }

                public void setTable(List<String> Table) {
                    this.Table = Table;
                }

                public static class DataBeanXXXXX {
                    /**
                     * Name : Status
                     * Text : 运行状态
                     */

                    private String Name;
                    private String Text;

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
                    }

                    public String getText() {
                        return Text;
                    }

                    public void setText(String Text) {
                        this.Text = Text;
                    }
                }
            }
        }

        public static class EcEvalBean {
            /**
             * RunSC : {"Compared":["今日时长","昨日时长"],"Data":[{"Name":"RunSC","Text":"运行时长","Value":[5.63,10.27]}],"MaxY":10.27,"MinY":5.63,"NowTime":1505887694928}
             * RunEC : {"Compared":["今日能耗","昨日能耗"],"Data":[{"Name":"RunEC","Text":"运行耗电","Value":[3386.98,3609.27]}],"MaxY":3609.27,"MinY":3386.98,"NowTime":1505887694928}
             * desc : 1.今日温度28.0℃，比昨天低2.0℃  今日开机运行时长5.63小时，比昨日运行时长低4.64小时;
             */

            private RunSCBean RunSC;
            private RunECBean RunEC;
            private String desc;

            public RunSCBean getRunSC() {
                return RunSC;
            }

            public void setRunSC(RunSCBean RunSC) {
                this.RunSC = RunSC;
            }

            public RunECBean getRunEC() {
                return RunEC;
            }

            public void setRunEC(RunECBean RunEC) {
                this.RunEC = RunEC;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public static class RunSCBean {
                /**
                 * Compared : ["今日时长","昨日时长"]
                 * Data : [{"Name":"RunSC","Text":"运行时长","Value":[5.63,10.27]}]
                 * MaxY : 10.27
                 * MinY : 5.63
                 * NowTime : 1505887694928
                 */

                private double MaxY;
                private double MinY;
                private long NowTime;
                private List<String> Compared;
                private List<DataBeanXXXXXX> Data;

                public double getMaxY() {
                    return MaxY;
                }

                public void setMaxY(double MaxY) {
                    this.MaxY = MaxY;
                }

                public double getMinY() {
                    return MinY;
                }

                public void setMinY(double MinY) {
                    this.MinY = MinY;
                }

                public long getNowTime() {
                    return NowTime;
                }

                public void setNowTime(long NowTime) {
                    this.NowTime = NowTime;
                }

                public List<String> getCompared() {
                    return Compared;
                }

                public void setCompared(List<String> Compared) {
                    this.Compared = Compared;
                }

                public List<DataBeanXXXXXX> getData() {
                    return Data;
                }

                public void setData(List<DataBeanXXXXXX> Data) {
                    this.Data = Data;
                }

                public static class DataBeanXXXXXX {
                    /**
                     * Name : RunSC
                     * Text : 运行时长
                     * Value : [5.63,10.27]
                     */

                    private String Name;
                    private String Text;
                    private double[] Value;

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
                    }

                    public String getText() {
                        return Text;
                    }

                    public void setText(String Text) {
                        this.Text = Text;
                    }

                    public double[] getValue() {
                        return Value;
                    }

                    public void setValue(double[] Value) {
                        this.Value = Value;
                    }
                }
            }

            public static class RunECBean {
                /**
                 * Compared : ["今日能耗","昨日能耗"]
                 * Data : [{"Name":"RunEC","Text":"运行耗电","Value":[3386.98,3609.27]}]
                 * MaxY : 3609.27
                 * MinY : 3386.98
                 * NowTime : 1505887694928
                 */

                private double MaxY;
                private double MinY;
                private long NowTime;
                private List<String> Compared;
                private List<DataBeanXXXXXXX> Data;

                public double getMaxY() {
                    return MaxY;
                }

                public void setMaxY(double MaxY) {
                    this.MaxY = MaxY;
                }

                public double getMinY() {
                    return MinY;
                }

                public void setMinY(double MinY) {
                    this.MinY = MinY;
                }

                public long getNowTime() {
                    return NowTime;
                }

                public void setNowTime(long NowTime) {
                    this.NowTime = NowTime;
                }

                public List<String> getCompared() {
                    return Compared;
                }

                public void setCompared(List<String> Compared) {
                    this.Compared = Compared;
                }

                public List<DataBeanXXXXXXX> getData() {
                    return Data;
                }

                public void setData(List<DataBeanXXXXXXX> Data) {
                    this.Data = Data;
                }

                public static class DataBeanXXXXXXX {
                    /**
                     * Name : RunEC
                     * Text : 运行耗电
                     * Value : [3386.98,3609.27]
                     */

                    private String Name;
                    private String Text;
                    private double[] Value;

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
                    }

                    public String getText() {
                        return Text;
                    }

                    public void setText(String Text) {
                        this.Text = Text;
                    }

                    public double[] getValue() {
                        return Value;
                    }

                    public void setValue(double[] Value) {
                        this.Value = Value;
                    }
                }
            }
        }
    }
}
