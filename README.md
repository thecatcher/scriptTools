# scriptTools
some scriptTools for O&amp;M

## contents

### designUpdateTool
![Shippable](https://img.shields.io/shippable/5444c5ecb904a4b21567b0ff.svg?maxAge=2592000)    ![Shippable](https://img.shields.io/badge/Designed-Brady.Y.Gao-green.svg)
#### brief information
A dedicated tool used for updeate Design data. 
This tool read the design and update files and then fill the new info in the design data and construct a new file.


#### change log
- V1.2(2016/10/11 02:00 UTC+08:00)
  - add result display
  - fix a null pointer execption when add new data.
- V1.3(2016/10/12 22:00 UTC+08:00)
	- add new function to write back the result to design file
	- compare file by modefy time 


###Script Generator
![Shippable](https://img.shields.io/badge/Designed-Brady.Y.Gao-green.svg)

####brief information
a simple tool to generate scripts. it used for nodes batch operation, fill the parameters into template and export the script for every node. This tool can only support single script generation.

#### change log
- V1.0.2(2016/4/1)
	- single script generation
	- use excel file as parameters list.
- V1.0.3(2016/11/02)
	- change to use csv file format as parameter list
	
