using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    public class Class2 : Class1
    {

            //public string a = "init2";
            public Class2()
            {
            }

            public void Fun()
            {
                Console.WriteLine("C2:" + base.a);
            }
     }
}
