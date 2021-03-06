USE [FINAL2]
GO
/****** Object:  User [##MS_PolicyEventProcessingLogin##]    Script Date: 2021/6/23 下午 11:46:35 ******/
CREATE USER [##MS_PolicyEventProcessingLogin##] FOR LOGIN [##MS_PolicyEventProcessingLogin##] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [##MS_AgentSigningCertificate##]    Script Date: 2021/6/23 下午 11:46:35 ******/
CREATE USER [##MS_AgentSigningCertificate##] FOR LOGIN [##MS_AgentSigningCertificate##]
GO
/****** Object:  Table [dbo].[COUPON]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COUPON](
	[Coupon_ID] [int] IDENTITY(1000000001,1) NOT NULL,
	[Price_Cut] [varchar](10) NULL,
	[Percentage_Cut] [varchar](3) NULL,
	[Expire_Date] [date] NOT NULL,
 CONSTRAINT [PK_COUPON] PRIMARY KEY CLUSTERED 
(
	[Coupon_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[COUPON_POSSESSION]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COUPON_POSSESSION](
	[Customer_ID] [int] NOT NULL,
	[Coupon_ID] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CUSTOMER]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CUSTOMER](
	[Customer_ID] [int] IDENTITY(1000000001,1) NOT NULL,
	[C_Name] [nvarchar](50) NOT NULL,
	[C_Phone] [varchar](20) NOT NULL,
	[C_Address] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_CUSTOMER] PRIMARY KEY CLUSTERED 
(
	[Customer_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EMPLOYEE]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EMPLOYEE](
	[Employee_ID] [char](10) NOT NULL,
	[E_Name] [nvarchar](50) NOT NULL,
	[E_Phone] [varchar](20) NOT NULL,
	[E_Adress] [nvarchar](100) NOT NULL,
	[Salary] [varchar](10) NOT NULL,
	[Manager_ID] [char](10) NULL,
 CONSTRAINT [PK_EMPLOYEE] PRIMARY KEY CLUSTERED 
(
	[Employee_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[INGREDIENT]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[INGREDIENT](
	[Ingredient_ID] [int] IDENTITY(1000000001,1) NOT NULL,
	[Type] [nvarchar](20) NOT NULL,
	[Supplier_ID] [char](10) NOT NULL,
 CONSTRAINT [PK_INGREDIENT] PRIMARY KEY CLUSTERED 
(
	[Ingredient_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[INGREDIENT_DETAIL]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[INGREDIENT_DETAIL](
	[Ingredient_ID] [int] NOT NULL,
	[Amount] [varchar](10) NOT NULL,
	[Price] [varchar](10) NOT NULL,
	[Expire_Date] [date] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ORDER]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ORDER](
	[Order_ID] [int] IDENTITY(1000000001,1) NOT NULL,
	[Customer_ID] [int] NOT NULL,
	[Total] [varchar](10) NOT NULL,
	[Discount] [varchar](10) NOT NULL,
	[Extra_Request] [nvarchar](max) NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[Order_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ORDER_DETAIL]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ORDER_DETAIL](
	[Order_ID] [int] NOT NULL,
	[Date_Order] [date] NOT NULL,
	[Date_Arrived] [date] NOT NULL,
	[Date_Retrieved] [date] NULL,
	[Status] [varchar](20) NOT NULL,
 CONSTRAINT [PK_ORDER_DETAIL] PRIMARY KEY CLUSTERED 
(
	[Order_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ORDER_PRODUCT_LIST]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ORDER_PRODUCT_LIST](
	[Order_ID] [int] NOT NULL,
	[Product_ID] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PRODUCT]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PRODUCT](
	[Product_ID] [int] IDENTITY(1000000001,1) NOT NULL,
	[Recipe_ID] [int] NOT NULL,
	[Price] [varchar](10) NOT NULL,
	[Date_Made] [datetime] NOT NULL,
	[Expire_Date] [datetime] NOT NULL,
 CONSTRAINT [PK_PRODUCT] PRIMARY KEY CLUSTERED 
(
	[Product_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RECIPE]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RECIPE](
	[Recipe_ID] [int] IDENTITY(1000000001,1) NOT NULL,
	[R_Name] [nvarchar](20) NOT NULL,
	[Type] [nvarchar](20) NOT NULL,
	[Time_Consumed] [varchar](5) NOT NULL,
	[R_Detail] [nvarchar](max) NULL,
 CONSTRAINT [PK_RECIPE] PRIMARY KEY CLUSTERED 
(
	[Recipe_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RECIPE_INGREDIENT]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RECIPE_INGREDIENT](
	[Recipe_ID] [int] NOT NULL,
	[Ingredient_ID] [int] NOT NULL,
	[Amount] [varchar](10) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SUPPLIER]    Script Date: 2021/6/23 下午 11:46:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SUPPLIER](
	[Supplier_ID] [int] IDENTITY(1000000001,1) NOT NULL,
	[S_Phone] [varchar](20) NOT NULL,
	[S_Address] [nvarchar](100) NOT NULL,
	[S_Details] [nvarchar](max) NULL,
 CONSTRAINT [PK_SUPPLIER] PRIMARY KEY CLUSTERED 
(
	[Supplier_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[COUPON] ON;
INSERT [dbo].[COUPON] ([Coupon_ID], [Price_Cut], [Percentage_Cut], [Expire_Date]) VALUES (N'1000000001', N'30', NULL, CAST(N'2021-06-30' AS Date))
INSERT [dbo].[COUPON] ([Coupon_ID], [Price_Cut], [Percentage_Cut], [Expire_Date]) VALUES (N'1000000002', NULL, N'10', CAST(N'2021-06-30' AS Date))
SET IDENTITY_INSERT [dbo].[COUPON] OFF;
GO
SET IDENTITY_INSERT [dbo].[COUPON_POSSESSION] ON;
INSERT [dbo].[COUPON_POSSESSION] ([Customer_ID], [Coupon_ID]) VALUES (N'1000000001', N'1000000001')
INSERT [dbo].[COUPON_POSSESSION] ([Customer_ID], [Coupon_ID]) VALUES (N'1000000001', N'1000000002')
SET IDENTITY_INSERT [dbo].[COUPON_POSSESSION] OFF;
GO
SET IDENTITY_INSERT [dbo].[CUSTOMER] ON;
INSERT [dbo].[CUSTOMER] ([Customer_ID], [C_Name], [C_Phone], [C_Address]) VALUES (N'1000000001', N'Andy', N'0912345678', N'No. 44, Sec. 1, Yanping N. Rd., Datong Dist., Taipei City')
INSERT [dbo].[CUSTOMER] ([Customer_ID], [C_Name], [C_Phone], [C_Address]) VALUES (N'1000000002', N'Charolotte', N'0987654321', N'No. 31, Datong Rd., North Dist., Hsinchu City')
INSERT [dbo].[CUSTOMER] ([Customer_ID], [C_Name], [C_Phone], [C_Address]) VALUES (N'1000000003', N'Ae', N'0913467982', N'No. 153, Jiuru 4th Rd., Gushan Dist., Kaohsiung City')
INSERT [dbo].[CUSTOMER] ([Customer_ID], [C_Name], [C_Phone], [C_Address]) VALUES (N'1000000004', N'Present', N'0978456321', N'No. 226, Zhonghe Rd., Zhonghe Dist., New Taipei City')
INSERT [dbo].[CUSTOMER] ([Customer_ID], [C_Name], [C_Phone], [C_Address]) VALUES (N'1000000005', N'Sebastian', N'0918736452', N'No. 97, Bade 2nd Rd., Toufen City, Miaoli County')
SET IDENTITY_INSERT [dbo].[CUSTOMER] OFF;
GO
SET IDENTITY_INSERT [dbo].[EMPLOYEE] ON;
INSERT [dbo].[EMPLOYEE] ([Employee_ID], [E_Name], [E_Phone], [E_Adress], [Salary], [Manager_ID]) VALUES (N'1000000100', N'Andy', N'0945678123', N'No. 331, Taishun Rd., Beitun Dist., Taichung City', N'24000', N'1000000201')
INSERT [dbo].[EMPLOYEE] ([Employee_ID], [E_Name], [E_Phone], [E_Adress], [Salary], [Manager_ID]) VALUES (N'1000000101', N'John', N'0987142536', N'No. 87, Zhongyang Rd., Guanmiao Dist., Tainan City', N'32000', N'1000000201')
INSERT [dbo].[EMPLOYEE] ([Employee_ID], [E_Name], [E_Phone], [E_Adress], [Salary], [Manager_ID]) VALUES (N'1000000201', N'Lisa', N'0988413226', N'No. 13, Shifu Rd., Xinyi Dist., Taipei City', N'46000', NULL)
SET IDENTITY_INSERT [dbo].[EMPLOYEE] OFF;
GO
SET IDENTITY_INSERT [dbo].[INGREDIENT] ON;
INSERT [dbo].[INGREDIENT] ([Ingredient_ID], [Type], [Supplier_ID]) VALUES (N'1000000001', N'flour', N'1000000001')
INSERT [dbo].[INGREDIENT] ([Ingredient_ID], [Type], [Supplier_ID]) VALUES (N'1000000002', N'egg', N'1000000001')
INSERT [dbo].[INGREDIENT] ([Ingredient_ID], [Type], [Supplier_ID]) VALUES (N'1000000003', N'sugar', N'1000000001')
INSERT [dbo].[INGREDIENT] ([Ingredient_ID], [Type], [Supplier_ID]) VALUES (N'1000000004', N'honey', N'1000000002')
INSERT [dbo].[INGREDIENT] ([Ingredient_ID], [Type], [Supplier_ID]) VALUES (N'1000000005', N'chocolate', N'1000000002')
SET IDENTITY_INSERT [dbo].[INGREDIENT] OFF;
GO
SET IDENTITY_INSERT [dbo].[INGREDIENT_DETAIL] ON;
INSERT [dbo].[INGREDIENT_DETAIL] ([Ingredient_ID], [Amount], [Price], [Expire_Date]) VALUES (N'1000000001', N'100', N'1000', CAST(N'2021-08-31' AS Date))
INSERT [dbo].[INGREDIENT_DETAIL] ([Ingredient_ID], [Amount], [Price], [Expire_Date]) VALUES (N'1000000002', N'50', N'500', CAST(N'2021-07-15' AS Date))
INSERT [dbo].[INGREDIENT_DETAIL] ([Ingredient_ID], [Amount], [Price], [Expire_Date]) VALUES (N'1000000003', N'80', N'400', CAST(N'2021-07-31' AS Date))
INSERT [dbo].[INGREDIENT_DETAIL] ([Ingredient_ID], [Amount], [Price], [Expire_Date]) VALUES (N'1000000004', N'20', N'400', CAST(N'2021-09-15' AS Date))
INSERT [dbo].[INGREDIENT_DETAIL] ([Ingredient_ID], [Amount], [Price], [Expire_Date]) VALUES (N'1000000005', N'200', N'2000', CAST(N'2021-06-30' AS Date))
SET IDENTITY_INSERT [dbo].[INGREDIENT_DETAIL] OFF;
GO
SET IDENTITY_INSERT [dbo].[ORDER] ON;
INSERT [dbo].[ORDER] ([Order_ID], [Customer_ID], [Total], [Discount], [Extra_Request]) VALUES (N'1000000001', N'1000000001', N'90', N'30', NULL)
SET IDENTITY_INSERT [dbo].[ORDER] OFF;
GO
SET IDENTITY_INSERT [dbo].[ORDER_DETAIL] ON;
INSERT [dbo].[ORDER_DETAIL] ([Order_ID], [Date_Order], [Date_Arrived], [Date_Retrieved], [Status]) VALUES (N'1000000001', CAST(N'2021-05-31' AS Date), CAST(N'2021-06-01' AS Date), CAST(N'2021-06-01' AS Date), N'Delivering')
SET IDENTITY_INSERT [dbo].[ORDER_DETAIL] OFF;
GO
SET IDENTITY_INSERT [dbo].[ORDER_PRODUCT_LIST] ON;
INSERT [dbo].[ORDER_PRODUCT_LIST] ([Order_ID], [Product_ID]) VALUES (N'1000000001', N'1000000001')
INSERT [dbo].[ORDER_PRODUCT_LIST] ([Order_ID], [Product_ID]) VALUES (N'1000000001', N'1000000002')
SET IDENTITY_INSERT [dbo].[ORDER_PRODUCT_LIST] OFF;
GO
SET IDENTITY_INSERT [dbo].[PRODUCT] ON;
INSERT [dbo].[PRODUCT] ([Product_ID], [Recipe_ID], [Price], [Date_Made], [Expire_Date]) VALUES (N'1000000001', N'1000000001', N'50', CAST(N'2021-05-31T00:00:00.000' AS DateTime), CAST(N'2021-06-07T00:00:00.000' AS DateTime))
INSERT [dbo].[PRODUCT] ([Product_ID], [Recipe_ID], [Price], [Date_Made], [Expire_Date]) VALUES (N'1000000002', N'1000000002', N'40', CAST(N'2021-05-31T00:00:00.000' AS DateTime), CAST(N'2021-06-15T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[PRODUCT] OFF;
GO
SET IDENTITY_INSERT [dbo].[RECIPE] ON;
INSERT [dbo].[RECIPE] ([Recipe_ID], [R_Name], [Type], [Time_Consumed], [R_Detail]) VALUES (N'1000000001', N'honey cake', N'cake', N'50', NULL)
INSERT [dbo].[RECIPE] ([Recipe_ID], [R_Name], [Type], [Time_Consumed], [R_Detail]) VALUES (N'1000000002', N'chocolate cookies', N'cookies', N'60', NULL)
SET IDENTITY_INSERT [dbo].[RECIPE] OFF;
GO
SET IDENTITY_INSERT [dbo].[RECIPE_INGREDIENT] ON;
INSERT [dbo].[RECIPE_INGREDIENT] ([Recipe_ID], [Ingredient_ID], [Amount]) VALUES (N'1000000001', N'1000000001', N'10')
INSERT [dbo].[RECIPE_INGREDIENT] ([Recipe_ID], [Ingredient_ID], [Amount]) VALUES (N'1000000001', N'1000000002', N'3')
INSERT [dbo].[RECIPE_INGREDIENT] ([Recipe_ID], [Ingredient_ID], [Amount]) VALUES (N'1000000001', N'1000000003', N'5')
INSERT [dbo].[RECIPE_INGREDIENT] ([Recipe_ID], [Ingredient_ID], [Amount]) VALUES (N'1000000001', N'1000000004', N'2')
INSERT [dbo].[RECIPE_INGREDIENT] ([Recipe_ID], [Ingredient_ID], [Amount]) VALUES (N'1000000002', N'1000000001', N'4')
INSERT [dbo].[RECIPE_INGREDIENT] ([Recipe_ID], [Ingredient_ID], [Amount]) VALUES (N'1000000002', N'1000000002', N'1')
INSERT [dbo].[RECIPE_INGREDIENT] ([Recipe_ID], [Ingredient_ID], [Amount]) VALUES (N'1000000002', N'1000000003', N'3')
INSERT [dbo].[RECIPE_INGREDIENT] ([Recipe_ID], [Ingredient_ID], [Amount]) VALUES (N'1000000002', N'1000000005', N'10')
SET IDENTITY_INSERT [dbo].[RECIPE_INGREDIENT] OFF;
GO
SET IDENTITY_INSERT [dbo].[SUPPLIER] ON;
INSERT [dbo].[SUPPLIER] ([Supplier_ID], [S_Phone], [S_Address], [S_Details]) VALUES (N'1000000001', N'09876543210', N'No. 86, Heping St., Zhongzheng Dist., Keelung City', NULL)
INSERT [dbo].[SUPPLIER] ([Supplier_ID], [S_Phone], [S_Address], [S_Details]) VALUES (N'1000000002', N'09457162354', N'No. 123, Zhulin Rd., Longtan Dist., Taoyuan City', NULL)
SET IDENTITY_INSERT [dbo].[SUPPLIER] OFF;
GO
ALTER TABLE [dbo].[ORDER] ADD  CONSTRAINT [DF_Order_Total]  DEFAULT ((0)) FOR [Total]
GO
ALTER TABLE [dbo].[ORDER] ADD  CONSTRAINT [DF_Order_Discount]  DEFAULT ((0)) FOR [Discount]
GO
