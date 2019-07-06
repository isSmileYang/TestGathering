using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    public class Class1
    {

        public string a = "init1";
        public Class1()
        {
        }

        public void Fun1()
        {
            this.a = "34";
            Console.WriteLine("C1:" + a);
        }

        public void Fun2()
        {
            this.a = "56";
            Console.WriteLine("C1:" + a);
        }

    }
}
