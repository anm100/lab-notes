   M S B A M L   `   `   `  ����J  FWpfApplication1, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null1clr-namespace:WpfApplication1WpfApplication1  5   	        WpfApplication1.InsertNote   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   /localclr-namespace:WpfApplication1     5   	   -   5          � Title$  הוספת הערה��5   	   $	��350��6   $	��600��6)   .��5	      � $��0,0,-6.4,-0.2q�6    � RowDefinitions 5
   
   �� 5      $	��45* �6   �� 5      $	��62* �6   .+�5   
   �� -   5         ��Name$ textBox��5      $
��Left=�6#   $��68��6>   $��169,26.8,0,0q�6J    �� TextWrapping$
 Wrap=�6`   $	��Top=�6t   $	��130��6�   $��1��6�   $��RightToLeft=�6�   �� 5   
   -   5         ��Name$ label��5      $��
הערות��6   $
��Left=�6/   $��336,43.8,0,0q�6J   $	��Top=�6`   $��85��6x   $��1��6�   $��31��6�   $��RightToLeft=�6�   �� 5   
   -   5         ��Name$ button��5      $��הוסף��6!   $
��Left=�60   $��346,136.8,0,0q�6K   $��1��6b   $	��Top=�6o   $��75��6�   $��27��6�   (  "System.Windows.Controls.DatePicker  5   
   -   5          Name$ datetime��5      $
��Left=�6'   $��
169,98,0,0q�6C   $	��Top=�6W      SelectedDateFormat$ Short=�6o   $	��130��6�   $��26��6�    � 	Resources 5      :  4System.Windows.Controls.Primitives.DatePickerTextBox	 �� 
TargetType
 �� Property �� Value �� Path �� RelativeSource �� AncestorType �� StringFormat��%�   '        �� 5      "	  6   .�5      �� 
 � �� 5      �� 5      .R�5   "   �� $ PART_TextBox��6*   ��5   %   �� $ SelectedDate� �� "  $ {0:dd/MM/yyyy}��5      5      5      5      5      �� 5   
   -   5        $ button1��5      $
��Left=�6"   $��169,136.8,0,0q�6=   $��1��6T   $	��Top=�6a   $��75��6y   $��27��6�   $��חזרה��6�   6�   �� 5    
   -   5        $ label1��5       $��RightToLeft=�6    $��בתאריך��6<   $
��Left=�6M   $��336,100,0,0q�6h   $	��Top=�6}   $��81��6�   5"      5#      