   M S B A M L   `   `   `  ����J  FWpfApplication1, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null1clr-namespace:WpfApplication1WpfApplication1  5   	   $    WpfApplication1.ShowLaboratory   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   /localclr-namespace:WpfApplication1     5   	   -   5          � Title$  הצגת מעבדות��5   	   $	��350��6   $	��600��6*   .��5	      � .+�5
   
   &   System.Windows.Controls.DataGrid  -   5          Name$ dataGrid��5
         CanUserAddRows .  $��RightToLeft=�6=   $
��Left=�6�      AutoGenerateColumns .  $��10,19,-8.4,0q�6�   $	��Top=�6�      ColumnWidth5  /System.Windows.Controls.DataGridLengthConverter$ * 6   $	��200��6  ��6�   ��    Columns 5      0  *System.Windows.Controls.DataGridTextColumn  5      ,  &System.Windows.Controls.DataGridColumn   Header$ שם טכנאי��6%      Width$ * 67      
IsReadOnly . 1  +System.Windows.Controls.DataGridBoundColumn	   Binding	 5   (   �� *NAME+5   (     5      $ כתובת הטכנאי��6%   $ * 6;    . 	 5   (   �� *	ADDRESS+5   (     5      $ שם המטופל��6%   $ * 68    . 	 5   (   �� *Patient_Name+5   (     5      $" פלפון של המטופל��6%   $ * 6>    . 	 5   (   �� *Patient_Phone+5   (     5      $ מתאריך��6%   $ * 65    . 	 5   (   �� *	IN_TIME+
 �� StringFormat$
 {0:dd/MM/yyy}��5   (     5      $ עד תאריך��6%   $ * 67    . 	 5   (   �� *
OUT_TIME+$
 {0:dd/MM/yyy}��5   (     5      $ 
הערות��6%   $ * 64    . 	 5   (   �� *	DETAILS+5    (   5"      �� 5#   
   -   5         ��Name$ button��5#      $
��Left=�6!   $��
שמירה��6<   $��389,264,0,0q�6c   $	��Top=�6x   $��84��6�   $��27��6�   6�   �� 5$   
   -   5        $ button2��5$      $��חזרה��6"   $
��Left=�61   $��136,264,0,0q�6L   $	��Top=�6a   $��97��6y   $��27��6�   5&      5'      