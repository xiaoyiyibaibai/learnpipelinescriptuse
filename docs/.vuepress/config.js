module.exports = {
  base: '/',
  title: '工程效率平台',
  markdown:{
  	lineNumbers:true  // 代码块显示行号
  },
    head: [
        ['link', { rel: 'icon', href: "./imgs/timg.jpg" }]
    ],
  themeConfig: {
        nav: [
            { text: '主页', link: '/' },
            { text: '快速上手', link: '/guid.md' },
            { text: '功能', link: '/about2.md' },
            { text: '设计', link: '/about2.md' },
            { text: '手册',
                items: [
                    { text: 'Web', link: '/web/' },
                    { text: 'vue', link: '/vue/' },
                    { text: 'ONES 帮助手册', link: '/Ones/' }
                ]
            },
            { text: '更多', link: '/about/' },
            { text: '关于2', link: '/about2.md' },
            { text: '参考文档', link: 'https://segmentfault.com/a/1190000016333850'}, // 外部链接
        ],
        sidebar: {
            '/java/': [{
                title: "java学习",
                collapsable: false,
                children: [
                    "",
                    "java1",
                    "java2",
                    "java3",
                    ['/java4', 'Explicit link text']  //[link,text] 形式的数组
                 ]
                },
                {
                    title: "java学习2",
                    collapsable: true,
                    children: [
                        "",
                        "java1",
                        "java2",
                        "java3",
                        "java4",
                    ]
                },
                {
                    title: "java学习3",
                    collapsable: true,
                    children: [
                        "",
                        "java1",
                        "java2",
                        "java3",
                        "java4",
                    ]
                }

              ],
            "/vue/": [{
                title: "vue学习",
                children: [
                "",
                "vue1",
                "vue2",
                "vue3",
                ]
              }
            ],
              "/web/": [{
                  title: "web学习",
                  children: [
                        "",
                        "web1",
                        "web2",
                        "web3",
                  ]
                }
              ],
            "/Ones/": [{
                title: "ONES 帮助手册",
                children: [
                    "",
                    "About",
                    "productinstruction.md",
                    "quicklystart",
                ]
            }
            ],
          },
          sidebarDepth: 3,  // e'b将同时提取markdown中h2 和 h3 标题，显示在侧边栏上
          lastUpdated: 'Last Updated',   // 文档更新时间：每个文件git最后提交的时间
  },
}