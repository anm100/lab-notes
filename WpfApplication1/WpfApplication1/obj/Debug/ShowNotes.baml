   M S B A M L   `   `   `  ����J  FWpfApplication1, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null1clr-namespace:WpfApplication1WpfApplication1  5   	       WpfApplication1.ShowNotes   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   /localclr-namespace:WpfApplication1     5   	   -   5          � Title$  הצגת הערות��5   	   $	��350��6   $	��600��6)   .��5	      � .+�5
   
   &   System.Windows.Controls.DataGrid  -   5          Name$ dataGrid��5
      $��RightToLeft=�6%      CanUserAddRows .     AutoGenerateColumns .     ColumnWidth5  /System.Windows.Controls.DataGridLengthConverter$ * 6t   $
��Left=�6�   $��0,18,0,0q�6�   $	��Top=�6�   $	��200��6�   ��6�   ��    Columns 5      0  *System.Windows.Controls.DataGridTextColumn  5      ,  &System.Windows.Controls.DataGridColumn   Header$ 
תאריך��6%      Width$ * 64      
IsReadOnly . 1  +System.Windows.Controls.DataGridBoundColumn	   Binding	 5   (   �� *DATE+
 �� StringFormat$
 {0:dd/MM/yyy}��5   (     5      $ 
הערות��6%   $ * 64    . 	 5   (   �� *	DETAILS+5   (   5      �� 5   
   -   5         ��Name$ button��5      $��
שמירה��6!   $
��Left=�61   $��322,266,0,0q�6L   $	��Top=�6a   $	��110��6y   $��30��6�   �� 5   
   -   5        $ button1��5      $��	חזרה ��6"   $
��Left=�62   $��129,266,0,0q�6M   $	��Top=�6b   $	��109��6z   $��30��6�   5      5      