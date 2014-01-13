package jmfs;
/* 
	Addon to Java MFS (jmfs) - Copyright (C) 2014 Keith Baker
	Contact: kbaker@alumni.ithaca.edu 

	This file is to be used with jmfs.

	jmfs is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	jmfs is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tivo.Mfs;
import tivo.disk.AppleDisk;
import tivo.disk.PartitionEntry;
import tivo.disk.TivoDisk;
import tivo.io.JavaLog;
import tivo.io.Utils;

public class FreeClear {
    private static final JavaLog log = JavaLog.getLog( FreeClear.class );
	
    public static void clear(String disk) throws Exception {
	log.debug( "bootDisk='%s'", disk );
	
	AppleDisk target = new AppleDisk(disk, true);
	
	List<PartitionEntry> partitions = target.getPartitions();
	if (partitions.get(partitions.size() - 1).getType().equals("Apple_Free")){
	    Utils.log( "Found Apple_Free Parition on the end of your disk, cleaning up\n", disk );

	    target.delPartition(partitions.size() - 1);
	    target.write();

	    Utils.log( "Cleared Apple_Free from '%s'\n", disk );
	}
	else {
	    Utils.log( "Could not find Apple_Free at the end of %s\n", disk );
	}
    }
	
    public static void main(String[] args) throws java.lang.Exception{
	FreeClear.clear(args[0]);
    }
}
