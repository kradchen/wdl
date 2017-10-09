using System;
using System.Collections.Generic;
using System.Windows.Forms;
using ECGExplore;
using System.Threading;
using PubLib;
using System.Reflection;
using System.Diagnostics;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.InteropServices;
using System.Net.NetworkInformation;

namespace cnris
{
    /// <summary>
    /// 本类封装了一些进程间通讯的细节
    /// </summary>
    public class WinMessageUtil
    {
        public const int WM_COPYDATA = 0x004A;

        //通过窗口的标题来查找窗口的句柄
        [DllImport("User32.dll", EntryPoint = "FindWindow")]
        private static extern int FindWindow(string lpClassName, string lpWindowName);

        //在DLL库中的发送消息函数
        [DllImport("User32.dll", EntryPoint = "SendMessage")]
        private static extern int SendMessage
            (
            int hWnd,                        // 目标窗口的句柄  
            int Msg,                        // 在这里是WM_COPYDATA
            int wParam,                    // 第一个消息参数
           ref  ECGExplore.MainForm.CopyDataStruct lParam        // 第二个消息参数
           );

        /// <summary>
        /// 发送消息，只能传递一个自定义的消息ID和消息字符串，想传一个结构，但没成功
        /// </summary>
        /// <param name="destProcessName">目标进程名称，如果有多个，则给每个都发送</param>
        /// <param name="msgID">自定义数据，可以通过这个来决定如何解析下面的strMsg</param>
        /// <param name="strMsg">传递的消息，是一个字符串</param>
        public static void SendMessage(string destProcessName, int msgID, string strMsg)
        {
            if (strMsg == null)
                return;

            //按进程名称查找，同名称的进程可能有许多，所以返回的是一个数组
            Process[] foundProcess = Process.GetProcessesByName(destProcessName);
            foreach (Process p in foundProcess)
            {
                int toWindowHandler = p.MainWindowHandle.ToInt32();
                if (toWindowHandler != 0)
                {
                    ECGExplore.MainForm.CopyDataStruct cds;
                    cds.dwData = (IntPtr)msgID;   //这里可以传入一些自定义的数据，但只能是4字节整数      
                    cds.lpData = strMsg;            //消息字符串
                    cds.cbData = System.Text.Encoding.Default.GetBytes(strMsg).Length + 1;  //注意，这里的长度是按字节来算的

                    //发送方的窗口的句柄, 由于本系统中的接收方不关心是该消息是从哪个窗口发出的，所以就直接填0了
                    int fromWindowHandler = 0;
                    SendMessage(toWindowHandler, WM_COPYDATA, fromWindowHandler, ref  cds);
                }
            }
        }

        /// <summary>
        /// 接收消息，得到消息字符串
        /// </summary>
        /// <param name="m">System.Windows.Forms.Message m</param>
        /// <returns>接收到的消息字符串</returns>
        public static string ReceiveMessage(ref  System.Windows.Forms.Message m)
        {
            ECGExplore.MainForm.CopyDataStruct cds = (ECGExplore.MainForm.CopyDataStruct)m.GetLParam(typeof(ECGExplore.MainForm.CopyDataStruct));
            return cds.lpData;
        }
    }

    static class Program
    {
        public const int WM_COPYDATA = 0x004A;

        //通过窗口的标题来查找窗口的句柄
        [DllImport("User32.dll", EntryPoint = "FindWindow")]
        private static extern int FindWindow(string lpClassName, string lpWindowName);

        //在DLL库中的发送消息函数
        [DllImport("User32.dll", EntryPoint = "SendMessage")]
        private static extern int SendMessage
            (
            int hWnd,                        // 目标窗口的句柄  
            int Msg,                        // 在这里是WM_COPYDATA
            int wParam,                    // 第一个消息参数
           ref  ECGExplore.MainForm.CopyDataStruct lParam        // 第二个消息参数
           );

        /// <summary> 
        /// 该函数设置由不同线程产生的窗口的显示状态。 
        /// </summary> 
        /// <param name="hWnd">窗口句柄</param> 
        /// <param name="cmdShow">指定窗口如何显示。查看允许值列表，请查阅ShowWlndow函数的说明部分。</param> 
        /// <returns>如果函数原来可见，返回值为非零；如果函数原来被隐藏，返回值为零。</returns> 
        [DllImport("User32.dll")]
        private static extern bool ShowWindowAsync(IntPtr hWnd, int cmdShow);
        /// <summary> 
        /// 该函数将创建指定窗口的线程设置到前台，并且激活该窗口。键盘输入转向该窗口，并为用户改各种可视的记号。系统给创建前台窗口的线程分配的权限稍高于其他线程。 
        /// </summary> 
        /// <param name="hWnd">将被激活并被调入前台的窗口句柄。</param> 
        /// <returns>如果窗口设入了前台，返回值为非零；如果窗口未被设入前台，返回值为零。</returns> 
        [DllImport("User32.dll")]
        private static extern bool SetForegroundWindow(IntPtr hWnd);
        private const int WS_SHOWNORMAL = 1; 

        static string[] mArgs = new string[] { };
        static System.Collections.Hashtable mArgsTable = new System.Collections.Hashtable();
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main(string[] args)
        {
            mArgs = args;
            try
            {
                for (int i = 0; i < args.Length - 1; i = i +2)
                {
                    mArgsTable.Add(args[i], args[i + 1]);
                }
            }
            catch 
            {

                mArgs = new string[] { };
                mArgsTable = new System.Collections.Hashtable();
            }
            
            DevExpress.UserSkins.OfficeSkins.Register();
            DevExpress.UserSkins.BonusSkins.Register();

            //窗体默认样式
            //string skinName = "Sharp Plus"; //e.Item.Caption.Replace(skinMask, "");
            //DevExpress.LookAndFeel.UserLookAndFeel.Default.SetSkinStyle(skinName);
            DevExpress.LookAndFeel.UserLookAndFeel.Default.SkinName = "Dark Side";
            DevExpress.Skins.SkinManager.EnableFormSkins();  //窗体边框样式同步


            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Process instance = RunningInstance();
            if (mArgs.Length > 0)
            {
                if (instance == null)
                {
                    //MessageBox.Show("", "没有");
                }
                else
                {
                    HandleRunningInstance(instance);
                    Process current = Process.GetCurrentProcess();
                    current.Kill();
                }
            }
            //处理出错信息
//#if !DEBUG
            Application.ThreadException += new ThreadExceptionEventHandler(Application_ThreadException);

            Application.SetUnhandledExceptionMode(UnhandledExceptionMode.CatchException);
            AppDomain.CurrentDomain.UnhandledException += new UnhandledExceptionEventHandler(CurrentDomain_UnhandledException);
//#endif

            if (args.Length <= 0)
            {
            Splasher.Show(args);
            while (Splasher.nResult == 0)
            {
                Thread.Sleep(100);
            }
            Splasher.nResult = 0;

            Splasher.Status = "正在初始化，请稍候……";
            Splasher.DisableLogin();

            
            #region 获取常用配置信息 icnris_runparam
            #endregion
            string mUnionMACCheck = "0";
            string mUnion = "0";
            string mCurMac = "";

                //获取MAC地址
            List<string> mMac = GetMacByNetworkInterface();
            if (mMac.Count > 0)
            {
                //获取登录信息
                mCurMac = mMac[0];
            }


            //这里临时调用一下，提高速度
            uDataSet mDsDateTime = new uDataSet(new string[] { 
                "select to_char(sysdate, 'yyyy-mm-dd') as ServerData,to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') as ServerDataTime from dual",
            " select t.cparamcode from icnris_runparam t where t.cparamtype = 'UNION' ",
            " select t.cparamcode from icnris_runparam t where t.cparamtype = 'UNIONMACCHECK' ",
            }, 
                new PubLib.uParameter[] { });
            mDsDateTime.Tables[0].TableName = "time";
            mDsDateTime.Tables[1].TableName = "union";
            mDsDateTime.Tables[2].TableName = "unioncmacheck";
            //获取和判断系统时间
            if (System.DateTime.Today.ToString("yyyy-MM-dd") != mDsDateTime.GetItemString(1, "ServerData"))
            {
                DevExpress.XtraEditors.XtraMessageBox.Show("系统日期与服务器不符", "提示");
                return;
            }

            #region 处理登录信息
            //获取是否是多院区的标志
            if (mDsDateTime.RowCount("union")>0)
            {
                if (mDsDateTime.GetItemString("union", 1, "cparamcode") == "1")
                {
                    mUnion = "1";
                }
            }

            if (mDsDateTime.RowCount("unioncmacheck") > 0)
            {
                if (mDsDateTime.GetItemString("unioncmacheck", 1, "cparamcode") == "1")
                {
                    mUnionMACCheck = "1";
                }
            }
            

            
            #endregion


            Splasher.Status = "请登录";
                Splasher.EnableLogin();
                while (Splasher.nResult == 0)
                {
                    Thread.Sleep(100);
                }

                //登录成功
                if (Splasher.nResult == 1)
                {
                    DoStartup(mArgsTable);
                }

                //关闭登录窗口
                Splasher.Close();
            }
            else
            {
                #region
                DoStartup(mArgsTable);
                #endregion
            }
        }



        static void DoStartup(System.Collections.Hashtable pArgsTable)
        {
            
            //记录系统为运行程序模式

            MainForm mainForm = null;
            if (pArgsTable.Count > 0)
            {
                #region 自动处理登录

                bool isExist = false;
                string userId = pArgsTable["-usercode"].ToString();
                string password = pArgsTable["-userpassword"].ToString();
                string correctPassword = "";
                //uDataSet mDs;
                string mStrSql = @"select user_pwd , grade from icnris_userinfo where user_id=:userid";
                string mStrRyYqDz = @"select distinct t.cyqdm from icnris_ryksdz t where t.cw <> 0 and cuser_id=:userid ";
                string[] mSql = { mStrSql, mStrRyYqDz };     //根据用户ID返回密码

                uDataSet mDs = new uDataSet(mSql, new uParameter[] { new uParameter(":userid", userId, 0), new uParameter(":userid", userId, 1) });
                mDs.Tables[0].TableName = "userinfo";
                mDs.Tables[1].TableName = "ryyqdz";
                if (mDs.RowCount() > 0)
                {
                    correctPassword = mDs.GetItemString(1, "user_pwd");
                }
                if (PubFunction.MD5Str(password) == (correctPassword))
                {

                    //如果是超级用户，不判断院区

                    isExist = true;


                }
                if (isExist)
                {
                    string mStrSqlInfo = @"select 
                                       *
                                       from icnris_userinfo t
                                       where 
                                          t.user_id=:userid";

                    uDataSet mUds = new uDataSet(new string[] { mStrSqlInfo }, new uParameter[] { new uParameter(":userid", userId) });
                    if (mUds.Tables[0].Rows.Count > 0)
                    {
                        //记录登录者信息
                        SuperBaseClass.BaseXtraFrom.G_UserCode = mUds.GetItemString(1, "user_id");
                        SuperBaseClass.BaseXtraFrom.G_UserName = mUds.GetItemString(1, "USER_NAME");
                        //SuperBaseClass.BaseXtraFrom.G_Gw = mUds.GetItemString(1, "GWMC");
                        SuperBaseClass.BaseXtraFrom.G_UserJB = mUds.GetItemString(1, "GRADE");
                        SuperBaseClass.BaseXtraFrom.G_UserLanguage = mUds.GetItemString(1, "LANGUAGE");
                        SuperBaseClass.BaseXtraFrom.G_Area = mUds.GetItemString(1, "CAREA");
                        SuperBaseClass.BaseXtraFrom.G_UserPacsPwd = mUds.GetItemString(1, "cpacspass");
                        SuperBaseClass.BaseXtraFrom.G_IsSuperQx = mUds.GetItemString(1, "reportdoc");
                        //wzx 添加
                        SuperBaseClass.BaseXtraFrom.G_UserKsCode = mUds.GetItemString(1, "CUSER_KSDM");
                        SuperBaseClass.BaseXtraFrom.G_hisYgdm = mUds.GetItemString(1, "CHIS_YGDM");
                        //人员所在科室，G_Ks记录本机所在科室
                        SuperBaseClass.BaseXtraFrom.G_UserKsName = mUds.GetItemString(1, "USERFLAG");
                        //院区信息
                        if (SuperBaseClass.BaseXtraFrom.G_IsUnion)
                        {
                            //用户所属院区，G_YQ登记本机所属院区
                            SuperBaseClass.BaseXtraFrom.G_UserYqName = mUds.GetItemString(1, "cyqbm");
                        }
                        //此处处理调用PACS的密码
                        //if (!string.IsNullOrEmpty(G_UserPacsPwd))
                        //{
                        //    try
                        //    {
                        //        SuperBaseClass.BaseXtraFrom.G_UserPacsPwd = PubFunction.DES(SuperBaseClass.BaseXtraFrom.G_UserPacsPwd, 1);
                        //    }
                        //    catch (Exception Ex)
                        //    {

                        //        //解密失败后，直接置空
                        //        SuperBaseClass.BaseXtraFrom.G_UserPacsPwd = "";
                        //    }
                        //}

                        if (string.IsNullOrEmpty(SuperBaseClass.BaseXtraFrom.G_UserLanguage))
                        {
                            SuperBaseClass.BaseXtraFrom.G_UserLanguage = "CHN";
                        }
                        string[] m_userList = new string[10];
                        m_userList[0] = SuperBaseClass.BaseXtraFrom.G_UserCode;
                        int j = 0;
                        
                    }

                    //登录成功
                    //CfgSysConfig.GI_LonginArea = cmb_area.Text;
                    //G_LonginArea = cmb_area.Text;


                    //处理权限信息
                    string[] mGetQxUserSql = new string[] {
                                    @" select * from  icnris_rygwdz where cuser_id = :user_id " ,
                                    @" select * from  icnris_ryksdz a ,icnris_ks k  where a.cksbm=k.cksbm and cuser_id = :user_id " ,
                                    @" select distinct c.iroomid,a.cuser_id,a.cksbm,a.cw,c.croom  from icnris_ks k ,icnris_checkroom c,icnris_ryksdz a where k.cksbm = a.cksbm and k.cksmc = c.cdept and   a.cuser_id = :user_id " ,
                                    @" select distinct c.iroomid,a.cuser_id,a.cksbm,a.cw,c.croom  from icnris_ks k ,icnris_uis_checkroom c,icnris_ryksdz a where k.cksbm = a.cksbm and k.cksmc = c.cdept and   a.cuser_id = :user_id " ,
                                    @" select distinct c.iroomid,a.cuser_id,a.cksbm,a.cw,c.croom  from icnris_ks k ,icnris_eis_checkroom c,icnris_ryksdz a where k.cksbm = a.cksbm and k.cksmc = c.cdept and   a.cuser_id = :user_id" ,
                                    @" select distinct t.cyqdm from icnris_ryksdz t where t.cw <> 0 and cuser_id=:user_id "
                    
                    };
                    uDataSet mGetQxUserDs = new uDataSet(
                        mGetQxUserSql, new uParameter[] 
                        { new uParameter("user_id", SuperBaseClass.BaseXtraFrom.G_UserCode), 
                          new uParameter("user_id", SuperBaseClass.BaseXtraFrom.G_UserCode,1),
                        new uParameter("user_id", SuperBaseClass.BaseXtraFrom.G_UserCode,2),
                        new uParameter("user_id", SuperBaseClass.BaseXtraFrom.G_UserCode,3),
                        new uParameter("user_id", SuperBaseClass.BaseXtraFrom.G_UserCode,4),
                        new uParameter("user_id", SuperBaseClass.BaseXtraFrom.G_UserCode,5) 
                        }
                            );

                    //获取登录成功用户的相关权限
                    mGetQxUserDs.Tables[0].TableName = "gw";
                    mGetQxUserDs.Tables[1].TableName = "ks";
                    mGetQxUserDs.Tables[2].TableName = "risks";
                    mGetQxUserDs.Tables[3].TableName = "uisks";
                    mGetQxUserDs.Tables[4].TableName = "eisks";
                    mGetQxUserDs.Tables[5].TableName = "yq";

                    SuperBaseClass.BaseXtraFrom.G_QxkS = new System.Collections.Hashtable();
                    SuperBaseClass.BaseXtraFrom.G_QxRy = new System.Collections.Hashtable();
                    SuperBaseClass.BaseXtraFrom.G_QxRisKs2Room = new System.Collections.Hashtable();
                    SuperBaseClass.BaseXtraFrom.G_QxUisKs2Room = new System.Collections.Hashtable();
                    SuperBaseClass.BaseXtraFrom.G_QxEisKs2Room = new System.Collections.Hashtable();
                    SuperBaseClass.BaseXtraFrom.G_QxYq = new System.Collections.Hashtable();

                    for (int i = 1; i <= mGetQxUserDs.RowCount("ks"); i++)
                    {
                        SuperBaseClass.BaseXtraFrom.G_QxkS.Add(mGetQxUserDs.GetItemString("ks", i, "cksbm"), mGetQxUserDs.GetItemString("ks", i, "cw"));
                    }
                    for (int i = 1; i <= mGetQxUserDs.RowCount("gw"); i++)
                    {
                        SuperBaseClass.BaseXtraFrom.G_QxRy.Add(mGetQxUserDs.GetItemString("gw", i, "cuser_id2"), mGetQxUserDs.GetItemString("gw", i, "cw"));
                    }

                    for (int i = 1; i <= mGetQxUserDs.RowCount("risks"); i++)
                    {
                        SuperBaseClass.BaseXtraFrom.G_QxRisKs2Room.Add(mGetQxUserDs.GetItemString("risks", i, "croom"), mGetQxUserDs.GetItemString("risks", i, "cw"));
                    }
                    for (int i = 1; i <= mGetQxUserDs.RowCount("uisks"); i++)
                    {
                        SuperBaseClass.BaseXtraFrom.G_QxUisKs2Room.Add(mGetQxUserDs.GetItemString("uisks", i, "croom"), mGetQxUserDs.GetItemString("uisks", i, "cw"));
                    }
                    for (int i = 1; i <= mGetQxUserDs.RowCount("eisks"); i++)
                    {
                        SuperBaseClass.BaseXtraFrom.G_QxEisKs2Room.Add(mGetQxUserDs.GetItemString("eisks", i, "croom"), mGetQxUserDs.GetItemString("eisks", i, "cw"));
                    }
                    for (int i = 1; i <= mGetQxUserDs.RowCount("yq"); i++)
                    {
                        SuperBaseClass.BaseXtraFrom.G_QxYq.Add(mGetQxUserDs.GetItemString("yq", i, "cyqdm"), mGetQxUserDs.GetItemString("yq", i, "cyqdm"));
                    }
                }
                #endregion

                //实例化
                System.Collections.Hashtable mArgs = new System.Collections.Hashtable();
                mArgs.Add("window", pArgsTable["-window"].ToString());
                mArgs.Add("caccno", pArgsTable["-caccno"].ToString());
                mArgs.Add("iid", pArgsTable["-iid"].ToString());

                mArgs.Add("mode", pArgsTable["-mode"].ToString());
                mArgs.Add("server", pArgsTable["-server"].ToString());
                mArgs.Add("rw", pArgsTable["-rw"].ToString());
                mArgs.Add("str", pArgsTable["-str"].ToString());
                mArgs.Add("col", pArgsTable["-col"].ToString());

                mainForm = new MainForm(mArgs);
                
            }
            if (pArgsTable.Count > 0)
            {
                
            }
            else
            {
                mainForm = new MainForm();
            }

            MainForm.G_SystemRun = true;
            #region DLL
            Assembly mDllName;

            //让这个对象加载某个外部dll程序集信息.
            mDllName = Assembly.LoadFile(Application.StartupPath + @"\\HisInfo.dll");
            //反射出该dll程序集的名称信息.
            //Console.WriteLine(mDllName.GetName());

            //定义一个"类型信息"的对象.
            Type t_Get = mDllName.GetType("HisInfo.Get");
            Type t_Set = mDllName.GetType("HisInfo.Set");
            //
            object mAssemblyObject_Get = System.Activator.CreateInstance(t_Get);
            object mAssemblyObject_Set = System.Activator.CreateInstance(t_Set);

            //定义一个成员信息类对象数组,并从程序集中获取.
            MemberInfo[] info_Get = t_Get.GetMembers();
            MemberInfo[] info_Set = t_Set.GetMembers();


            //定义一个成员方法对象,这里是指定方法名称来获取的.
            MethodInfo mMethod_Check_Status = t_Get.GetMethod("f_Check_Status");
            MethodInfo mMethod_Get_Info = t_Get.GetMethod("f_Get_Info");
            MethodInfo mMethod_Set_Info = t_Set.GetMethod("f_Set_Info");

            MethodInfo mMethod_Set_Status = t_Set.GetMethod("f_Set_Status");

            MethodInfo mMethod_Get_BodyPart_1 = t_Get.GetMethod("f_Get_BodyPart_1");
            MethodInfo mMethod_Get_BodyPart_2 = t_Get.GetMethod("f_Get_BodyPart_2");
            MethodInfo mMethod_Get_BodyPart_3 = t_Get.GetMethod("f_Get_BodyPart_3");
            MethodInfo mMethod_Get_BodyPart = t_Get.GetMethod("f_Get_BodyPart");

            mainForm.SMethod_Check_Status = mMethod_Check_Status;
            mainForm.SMethod_Get_Info = mMethod_Get_Info;
            mainForm.SMethod_Set_Info = mMethod_Set_Info;
            mainForm.SMethod_Set_Status = mMethod_Set_Status;

            mainForm.SAssemblyObject_Get = mAssemblyObject_Get;
            mainForm.SAssemblyObject_Set = mAssemblyObject_Set;

            mainForm.SMethod_Get_BodyPart_1 = mMethod_Get_BodyPart_1;
            mainForm.SMethod_Get_BodyPart_2 = mMethod_Get_BodyPart_2;
            mainForm.SMethod_Get_BodyPart_3 = mMethod_Get_BodyPart_3;
            mainForm.SMethod_Get_BodyPart = mMethod_Get_BodyPart;

            #endregion

            Application.Run(mainForm);
        }

        public static string GetMethodInfo()
        {
            string str = "";
            //取得当前方法命名空间    
            str += "命名空间名:" + System.Reflection.MethodBase.GetCurrentMethod().DeclaringType.Namespace + "\r\n";
            //取得当前方法类全名 包括命名空间    
            str += "类名:" + System.Reflection.MethodBase.GetCurrentMethod().DeclaringType.FullName + "\r\n";
            //取得当前方法名    
            str += "方法名:" + System.Reflection.MethodBase.GetCurrentMethod().Name + "\r\n"; str += "\r\n";
            StackTrace ss = new StackTrace(true);
            MethodBase mb = ss.GetFrame(1).GetMethod();
            //取得父方法命名空间    
            str += mb.DeclaringType.Namespace + "\r\n";
            //取得父方法类名    
            str += mb.DeclaringType.Name + "\r\n";
            //取得父方法类全名    
            str += mb.DeclaringType.FullName + "\r\n";
            //取得父方法名    
            str += mb.Name + "\r\n";
            return str;
        }


        //出错处理
        static void CurrentDomain_UnhandledException(object sender, UnhandledExceptionEventArgs e)
        {
            //MessageBox.Show((e.ExceptionObject as Exception).Message);
            //Logger.Instance().Error(e.ExceptionObject);
            string mTitle="系统错误" ;
            string mObjectName = SuperBaseClass.uError.GetMethodInfo();
            string mDisp="系统错误";
            string mErrMsg = (e.ExceptionObject as Exception).Message + "\r\n" + (e.ExceptionObject as Exception).StackTrace;
            string mExErrMsg = (e.ExceptionObject as Exception).TargetSite + "\r\n" + (e.ExceptionObject as Exception).InnerException;

            SuperBaseClass.uMessageBox mMessageBox = new SuperBaseClass.uMessageBox(mTitle, mObjectName, mDisp, mErrMsg, mExErrMsg);

            mMessageBox.ShowDialog( );
        }

        static void Application_ThreadException(object sender, System.Threading.ThreadExceptionEventArgs e)
        {
            //MessageBox.Show(e.Exception.Message);
            //Logger.Instance().Error(e.Exception.Message, e.Exception);
            string mTitle = "系统错误";
            string mObjectName = SuperBaseClass.uError.GetMethodInfo();
            string mDisp = "系统错误";
            string mErrMsg = e.Exception.Message + "\r\n" + e.Exception.StackTrace;
            string mExErrMsg = e.Exception.TargetSite + "\r\n" + e.Exception.InnerException;

            SuperBaseClass.uMessageBox mMessageBox = new SuperBaseClass.uMessageBox(mTitle, mObjectName, mDisp, mErrMsg, mExErrMsg);

            mMessageBox.ShowDialog();

        }


        /// <summary> 
        /// 获取正在运行的实例，没有运行的实例返回null; 
        /// </summary> 
        public static Process RunningInstance()
        {
            Process current = Process.GetCurrentProcess();
            Process[] processes = Process.GetProcessesByName(current.ProcessName);
            foreach (Process process in processes)
            {
                if (process.Id != current.Id)
                {
                    if (Assembly.GetExecutingAssembly().Location.Replace("/", "\\") == current.MainModule.FileName)
                    {
                        return process;
                    }
                }
            }
            return null;
        }

        /// <summary> 
        /// 显示已运行的程序。 
        /// </summary> 
        public static void HandleRunningInstance(Process instance)
        {
            WinMessageUtil mWinMessageUtil = new WinMessageUtil();
            ShowWindowAsync(instance.MainWindowHandle, WS_SHOWNORMAL); //显示，可以注释掉 
            SetForegroundWindow(instance.MainWindowHandle);            //放到前端 
            ECGExplore.MainForm.CopyDataStruct cds;
            cds.dwData = (IntPtr)WM_COPYDATA;   //这里可以传入一些自定义的数据，但只能是4字节整数
            //4位版本|窗口|ID|类型|操作|操作参数
            cds.lpData = "0001|" + mArgsTable["-window"].ToString() + "|" + mArgsTable["-iid"].ToString() + "|" + mArgsTable["-mode"].ToString() + "|" + mArgsTable["-server"].ToString() + "|RW|0";            //消息字符串
            //string strMsg = "ooxx";
            cds.cbData = System.Text.Encoding.Default.GetBytes(cds.lpData).Length + 1;  //注意，这里的长度是按字节来算的

            //发送方的窗口的句柄, 由于本系统中的接收方不关心是该消息是从哪个窗口发出的，所以就直接填0了
            int fromWindowHandler = 0;
            SendMessage(instance.MainWindowHandle.ToInt32(), WM_COPYDATA, fromWindowHandler, ref  cds);

        }


        public static NetworkInterface[] NetCardInfo()
        {
            return NetworkInterface.GetAllNetworkInterfaces();
        }

        /// <summary>
        /// 返回网卡的MAC集合
        /// </summary>
        /// <returns></returns>
        public static List<string> GetMacByNetworkInterface()
        {
            List<string> macs = new List<string>();
            NetworkInterface[] interfaces = NetworkInterface.GetAllNetworkInterfaces();
            foreach (NetworkInterface ni in interfaces)
            {
                macs.Add(ni.GetPhysicalAddress().ToString());
            }
            return macs;
        }
    }
}
