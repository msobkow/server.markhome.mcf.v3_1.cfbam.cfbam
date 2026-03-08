// Description: Java 25 Table Object implementation for Table.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamTableTableObj
	implements ICFBamTableTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamTable.CLASS_CODE;
	protected static final int backingClassCode = ICFBamTable.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamTableObj> members;
	private Map<CFLibDbKeyHash256, ICFBamTableObj> allTable;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableObj > > indexByTenantIdx;
	private Map< ICFBamTableBySchemaDefIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableObj > > indexBySchemaDefIdx;
	private Map< ICFBamTableByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableObj > > indexByDefSchemaIdx;
	private Map< ICFBamTableByUNameIdxKey,
		ICFBamTableObj > indexByUNameIdx;
	private Map< ICFBamTableBySchemaCdIdxKey,
		ICFBamTableObj > indexBySchemaCdIdx;
	private Map< ICFBamTableByPrimaryIndexIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableObj > > indexByPrimaryIndexIdx;
	private Map< ICFBamTableByLookupIndexIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableObj > > indexByLookupIndexIdx;
	private Map< ICFBamTableByAltIndexIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableObj > > indexByAltIndexIdx;
	private Map< ICFBamTableByQualTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableObj > > indexByQualTableIdx;
	public static String TABLE_NAME = "Table";
	public static String TABLE_DBNAME = "tbldef";

	public CFBamTableTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
		allTable = null;
		indexByTenantIdx = null;
		indexBySchemaDefIdx = null;
		indexByDefSchemaIdx = null;
		indexByUNameIdx = null;
		indexBySchemaCdIdx = null;
		indexByPrimaryIndexIdx = null;
		indexByLookupIndexIdx = null;
		indexByAltIndexIdx = null;
		indexByQualTableIdx = null;
	}

	public CFBamTableTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
		allTable = null;
		indexByTenantIdx = null;
		indexBySchemaDefIdx = null;
		indexByDefSchemaIdx = null;
		indexByUNameIdx = null;
		indexBySchemaCdIdx = null;
		indexByPrimaryIndexIdx = null;
		indexByLookupIndexIdx = null;
		indexByAltIndexIdx = null;
		indexByQualTableIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamTableTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamTableTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allTable = null;
		indexByTenantIdx = null;
		indexBySchemaDefIdx = null;
		indexByDefSchemaIdx = null;
		indexByUNameIdx = null;
		indexBySchemaCdIdx = null;
		indexByPrimaryIndexIdx = null;
		indexByLookupIndexIdx = null;
		indexByAltIndexIdx = null;
		indexByQualTableIdx = null;
		List<ICFBamTableObj> toForget = new LinkedList<ICFBamTableObj>();
		ICFBamTableObj cur = null;
		Iterator<ICFBamTableObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamTableObj.
	 */
	@Override
	public ICFBamTableObj newInstance() {
		ICFBamTableObj inst = new CFBamTableObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamTableObj.
	 */
	@Override
	public ICFBamTableEditObj newEditInstance( ICFBamTableObj orig ) {
		ICFBamTableEditObj edit = new CFBamTableEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamTableObj realiseTable( ICFBamTableObj Obj ) {
		ICFBamTableObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTableObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamTableObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexBySchemaDefIdx != null ) {
				ICFBamTableBySchemaDefIdxKey keySchemaDefIdx =
					schema.getCFBamBackingStore().getFactoryTable().newBySchemaDefIdxKey();
				keySchemaDefIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapSchemaDefIdx = indexBySchemaDefIdx.get( keySchemaDefIdx );
				if( mapSchemaDefIdx != null ) {
					mapSchemaDefIdx.remove( keepObj.getPKey() );
					if( mapSchemaDefIdx.size() <= 0 ) {
						indexBySchemaDefIdx.remove( keySchemaDefIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTableByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamTableByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByUNameIdxKey();
				keyUNameIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexBySchemaCdIdx != null ) {
				ICFBamTableBySchemaCdIdxKey keySchemaCdIdx =
					schema.getCFBamBackingStore().getFactoryTable().newBySchemaCdIdxKey();
				keySchemaCdIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				keySchemaCdIdx.setRequiredTableClassCode( keepObj.getRequiredTableClassCode() );
				indexBySchemaCdIdx.remove( keySchemaCdIdx );
			}

			if( indexByPrimaryIndexIdx != null ) {
				ICFBamTableByPrimaryIndexIdxKey keyPrimaryIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByPrimaryIndexIdxKey();
				keyPrimaryIndexIdx.setOptionalPrimaryIndexId( keepObj.getOptionalPrimaryIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapPrimaryIndexIdx = indexByPrimaryIndexIdx.get( keyPrimaryIndexIdx );
				if( mapPrimaryIndexIdx != null ) {
					mapPrimaryIndexIdx.remove( keepObj.getPKey() );
					if( mapPrimaryIndexIdx.size() <= 0 ) {
						indexByPrimaryIndexIdx.remove( keyPrimaryIndexIdx );
					}
				}
			}

			if( indexByLookupIndexIdx != null ) {
				ICFBamTableByLookupIndexIdxKey keyLookupIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByLookupIndexIdxKey();
				keyLookupIndexIdx.setOptionalLookupIndexId( keepObj.getOptionalLookupIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapLookupIndexIdx = indexByLookupIndexIdx.get( keyLookupIndexIdx );
				if( mapLookupIndexIdx != null ) {
					mapLookupIndexIdx.remove( keepObj.getPKey() );
					if( mapLookupIndexIdx.size() <= 0 ) {
						indexByLookupIndexIdx.remove( keyLookupIndexIdx );
					}
				}
			}

			if( indexByAltIndexIdx != null ) {
				ICFBamTableByAltIndexIdxKey keyAltIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByAltIndexIdxKey();
				keyAltIndexIdx.setOptionalAltIndexId( keepObj.getOptionalAltIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapAltIndexIdx = indexByAltIndexIdx.get( keyAltIndexIdx );
				if( mapAltIndexIdx != null ) {
					mapAltIndexIdx.remove( keepObj.getPKey() );
					if( mapAltIndexIdx.size() <= 0 ) {
						indexByAltIndexIdx.remove( keyAltIndexIdx );
					}
				}
			}

			if( indexByQualTableIdx != null ) {
				ICFBamTableByQualTableIdxKey keyQualTableIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByQualTableIdxKey();
				keyQualTableIdx.setOptionalQualifyingTableId( keepObj.getOptionalQualifyingTableId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapQualTableIdx = indexByQualTableIdx.get( keyQualTableIdx );
				if( mapQualTableIdx != null ) {
					mapQualTableIdx.remove( keepObj.getPKey() );
					if( mapQualTableIdx.size() <= 0 ) {
						indexByQualTableIdx.remove( keyQualTableIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamTableObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaDefIdx != null ) {
				ICFBamTableBySchemaDefIdxKey keySchemaDefIdx =
					schema.getCFBamBackingStore().getFactoryTable().newBySchemaDefIdxKey();
				keySchemaDefIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapSchemaDefIdx = indexBySchemaDefIdx.get( keySchemaDefIdx );
				if( mapSchemaDefIdx != null ) {
					mapSchemaDefIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTableByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamTableByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByUNameIdxKey();
				keyUNameIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexBySchemaCdIdx != null ) {
				ICFBamTableBySchemaCdIdxKey keySchemaCdIdx =
					schema.getCFBamBackingStore().getFactoryTable().newBySchemaCdIdxKey();
				keySchemaCdIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				keySchemaCdIdx.setRequiredTableClassCode( keepObj.getRequiredTableClassCode() );
				indexBySchemaCdIdx.put( keySchemaCdIdx, keepObj );
			}

			if( indexByPrimaryIndexIdx != null ) {
				ICFBamTableByPrimaryIndexIdxKey keyPrimaryIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByPrimaryIndexIdxKey();
				keyPrimaryIndexIdx.setOptionalPrimaryIndexId( keepObj.getOptionalPrimaryIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapPrimaryIndexIdx = indexByPrimaryIndexIdx.get( keyPrimaryIndexIdx );
				if( mapPrimaryIndexIdx != null ) {
					mapPrimaryIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLookupIndexIdx != null ) {
				ICFBamTableByLookupIndexIdxKey keyLookupIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByLookupIndexIdxKey();
				keyLookupIndexIdx.setOptionalLookupIndexId( keepObj.getOptionalLookupIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapLookupIndexIdx = indexByLookupIndexIdx.get( keyLookupIndexIdx );
				if( mapLookupIndexIdx != null ) {
					mapLookupIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByAltIndexIdx != null ) {
				ICFBamTableByAltIndexIdxKey keyAltIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByAltIndexIdxKey();
				keyAltIndexIdx.setOptionalAltIndexId( keepObj.getOptionalAltIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapAltIndexIdx = indexByAltIndexIdx.get( keyAltIndexIdx );
				if( mapAltIndexIdx != null ) {
					mapAltIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByQualTableIdx != null ) {
				ICFBamTableByQualTableIdxKey keyQualTableIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByQualTableIdxKey();
				keyQualTableIdx.setOptionalQualifyingTableId( keepObj.getOptionalQualifyingTableId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapQualTableIdx = indexByQualTableIdx.get( keyQualTableIdx );
				if( mapQualTableIdx != null ) {
					mapQualTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allTable != null ) {
				allTable.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamTableObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allTable != null ) {
				allTable.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaDefIdx != null ) {
				ICFBamTableBySchemaDefIdxKey keySchemaDefIdx =
					schema.getCFBamBackingStore().getFactoryTable().newBySchemaDefIdxKey();
				keySchemaDefIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapSchemaDefIdx = indexBySchemaDefIdx.get( keySchemaDefIdx );
				if( mapSchemaDefIdx != null ) {
					mapSchemaDefIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTableByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamTableByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByUNameIdxKey();
				keyUNameIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexBySchemaCdIdx != null ) {
				ICFBamTableBySchemaCdIdxKey keySchemaCdIdx =
					schema.getCFBamBackingStore().getFactoryTable().newBySchemaCdIdxKey();
				keySchemaCdIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				keySchemaCdIdx.setRequiredTableClassCode( keepObj.getRequiredTableClassCode() );
				indexBySchemaCdIdx.put( keySchemaCdIdx, keepObj );
			}

			if( indexByPrimaryIndexIdx != null ) {
				ICFBamTableByPrimaryIndexIdxKey keyPrimaryIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByPrimaryIndexIdxKey();
				keyPrimaryIndexIdx.setOptionalPrimaryIndexId( keepObj.getOptionalPrimaryIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapPrimaryIndexIdx = indexByPrimaryIndexIdx.get( keyPrimaryIndexIdx );
				if( mapPrimaryIndexIdx != null ) {
					mapPrimaryIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLookupIndexIdx != null ) {
				ICFBamTableByLookupIndexIdxKey keyLookupIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByLookupIndexIdxKey();
				keyLookupIndexIdx.setOptionalLookupIndexId( keepObj.getOptionalLookupIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapLookupIndexIdx = indexByLookupIndexIdx.get( keyLookupIndexIdx );
				if( mapLookupIndexIdx != null ) {
					mapLookupIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByAltIndexIdx != null ) {
				ICFBamTableByAltIndexIdxKey keyAltIndexIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByAltIndexIdxKey();
				keyAltIndexIdx.setOptionalAltIndexId( keepObj.getOptionalAltIndexId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapAltIndexIdx = indexByAltIndexIdx.get( keyAltIndexIdx );
				if( mapAltIndexIdx != null ) {
					mapAltIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByQualTableIdx != null ) {
				ICFBamTableByQualTableIdxKey keyQualTableIdx =
					schema.getCFBamBackingStore().getFactoryTable().newByQualTableIdxKey();
				keyQualTableIdx.setOptionalQualifyingTableId( keepObj.getOptionalQualifyingTableId() );
				Map<CFLibDbKeyHash256, ICFBamTableObj > mapQualTableIdx = indexByQualTableIdx.get( keyQualTableIdx );
				if( mapQualTableIdx != null ) {
					mapQualTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamTableObj createTable( ICFBamTableObj Obj ) {
		ICFBamTableObj obj = Obj;
		ICFBamTable rec = obj.getTableRec();
		schema.getCFBamBackingStore().getTableTable().createTable(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamTableObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamTableObj readTable( CFLibDbKeyHash256 pkey ) {
		return( readTable( pkey, false ) );
	}

	@Override
	public ICFBamTableObj readTable( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamTableObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamTable readRec = schema.getCFBamBackingStore().getTableTable().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamTableObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamTableObj readCachedTable( CFLibDbKeyHash256 pkey ) {
		ICFBamTableObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeTable( ICFBamTableObj obj )
	{
		final String S_ProcName = "CFBamTableTableObj.reallyDeepDisposeTable() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTableObj existing = readCachedTable( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamTableBySchemaDefIdxKey keySchemaDefIdx = schema.getCFBamBackingStore().getFactoryTable().newBySchemaDefIdxKey();
		keySchemaDefIdx.setRequiredSchemaDefId( existing.getRequiredSchemaDefId() );

		ICFBamTableByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryTable().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamTableByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryTable().newByUNameIdxKey();
		keyUNameIdx.setRequiredSchemaDefId( existing.getRequiredSchemaDefId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamTableBySchemaCdIdxKey keySchemaCdIdx = schema.getCFBamBackingStore().getFactoryTable().newBySchemaCdIdxKey();
		keySchemaCdIdx.setRequiredSchemaDefId( existing.getRequiredSchemaDefId() );
		keySchemaCdIdx.setRequiredTableClassCode( existing.getRequiredTableClassCode() );

		ICFBamTableByPrimaryIndexIdxKey keyPrimaryIndexIdx = schema.getCFBamBackingStore().getFactoryTable().newByPrimaryIndexIdxKey();
		keyPrimaryIndexIdx.setOptionalPrimaryIndexId( existing.getOptionalPrimaryIndexId() );

		ICFBamTableByLookupIndexIdxKey keyLookupIndexIdx = schema.getCFBamBackingStore().getFactoryTable().newByLookupIndexIdxKey();
		keyLookupIndexIdx.setOptionalLookupIndexId( existing.getOptionalLookupIndexId() );

		ICFBamTableByAltIndexIdxKey keyAltIndexIdx = schema.getCFBamBackingStore().getFactoryTable().newByAltIndexIdxKey();
		keyAltIndexIdx.setOptionalAltIndexId( existing.getOptionalAltIndexId() );

		ICFBamTableByQualTableIdxKey keyQualTableIdx = schema.getCFBamBackingStore().getFactoryTable().newByQualTableIdxKey();
		keyQualTableIdx.setOptionalQualifyingTableId( existing.getOptionalQualifyingTableId() );


					schema.getServerMethodTableObj().deepDisposeServerMethodByMethTableIdx( existing.getRequiredId() );
					schema.getDelTopDepTableObj().deepDisposeDelTopDepByDelTopDepTblIdx( existing.getRequiredId() );
					schema.getClearTopDepTableObj().deepDisposeClearTopDepByClrTopDepTblIdx( existing.getRequiredId() );
					schema.getChainTableObj().deepDisposeChainByChainTableIdx( existing.getRequiredId() );
		ICFBamRelationObj objDelTableRelationPopDep;
		List<ICFBamRelationObj> arrDelTableRelationPopDep = schema.getRelationTableObj().readCachedRelationByRelTableIdx( existing.getRequiredId() );
		Iterator<ICFBamRelationObj> iterDelTableRelationPopDep = arrDelTableRelationPopDep.iterator();
		while( iterDelTableRelationPopDep.hasNext() ) {
			objDelTableRelationPopDep = iterDelTableRelationPopDep.next();
			if( objDelTableRelationPopDep != null ) {
						schema.getPopTopDepTableObj().deepDisposePopTopDepByContRelIdx( objDelTableRelationPopDep.getRequiredId() );
			}
		}
		ICFBamRelationObj objDelTableRelationCol;
		List<ICFBamRelationObj> arrDelTableRelationCol = schema.getRelationTableObj().readCachedRelationByRelTableIdx( existing.getRequiredId() );
		Iterator<ICFBamRelationObj> iterDelTableRelationCol = arrDelTableRelationCol.iterator();
		while( iterDelTableRelationCol.hasNext() ) {
			objDelTableRelationCol = iterDelTableRelationCol.next();
			if( objDelTableRelationCol != null ) {
						schema.getRelationColTableObj().deepDisposeRelationColByRelationIdx( objDelTableRelationCol.getRequiredId() );
			}
		}
					schema.getRelationTableObj().deepDisposeRelationByRelTableIdx( existing.getRequiredId() );
		ICFBamIndexObj objDelTableIndexRefRelFmCol;
		List<ICFBamIndexObj> arrDelTableIndexRefRelFmCol = schema.getIndexTableObj().readCachedIndexByIdxTableIdx( existing.getRequiredId() );
		Iterator<ICFBamIndexObj> iterDelTableIndexRefRelFmCol = arrDelTableIndexRefRelFmCol.iterator();
		while( iterDelTableIndexRefRelFmCol.hasNext() ) {
			objDelTableIndexRefRelFmCol = iterDelTableIndexRefRelFmCol.next();
			if( objDelTableIndexRefRelFmCol != null ) {
			ICFBamIndexColObj objColumns;
			List<ICFBamIndexColObj> arrColumns = schema.getIndexColTableObj().readCachedIndexColByIndexIdx( objDelTableIndexRefRelFmCol.getRequiredId() );
			Iterator<ICFBamIndexColObj> iterColumns = arrColumns.iterator();
			while( iterColumns.hasNext() ) {
				objColumns = iterColumns.next();
					schema.getRelationColTableObj().deepDisposeRelationColByFromColIdx( objColumns.getRequiredId() );
			}
			}
		}
		ICFBamIndexObj objDelTableIndexRefRelToCol;
		List<ICFBamIndexObj> arrDelTableIndexRefRelToCol = schema.getIndexTableObj().readCachedIndexByIdxTableIdx( existing.getRequiredId() );
		Iterator<ICFBamIndexObj> iterDelTableIndexRefRelToCol = arrDelTableIndexRefRelToCol.iterator();
		while( iterDelTableIndexRefRelToCol.hasNext() ) {
			objDelTableIndexRefRelToCol = iterDelTableIndexRefRelToCol.next();
			if( objDelTableIndexRefRelToCol != null ) {
			ICFBamIndexColObj objColumns;
			List<ICFBamIndexColObj> arrColumns = schema.getIndexColTableObj().readCachedIndexColByIndexIdx( objDelTableIndexRefRelToCol.getRequiredId() );
			Iterator<ICFBamIndexColObj> iterColumns = arrColumns.iterator();
			while( iterColumns.hasNext() ) {
				objColumns = iterColumns.next();
					schema.getRelationColTableObj().deepDisposeRelationColByToColIdx( objColumns.getRequiredId() );
			}
			}
		}
		ICFBamIndexObj objDelTableIndexCol;
		List<ICFBamIndexObj> arrDelTableIndexCol = schema.getIndexTableObj().readCachedIndexByIdxTableIdx( existing.getRequiredId() );
		Iterator<ICFBamIndexObj> iterDelTableIndexCol = arrDelTableIndexCol.iterator();
		while( iterDelTableIndexCol.hasNext() ) {
			objDelTableIndexCol = iterDelTableIndexCol.next();
			if( objDelTableIndexCol != null ) {
						schema.getIndexColTableObj().deepDisposeIndexColByIndexIdx( objDelTableIndexCol.getRequiredId() );
			}
		}
					schema.getIndexTableObj().deepDisposeIndexByIdxTableIdx( existing.getRequiredId() );
		ICFBamValueObj objDelTableRefIndexColumns;
		List<ICFBamValueObj> arrDelTableRefIndexColumns = schema.getValueTableObj().readCachedValueByScopeIdx( existing.getRequiredId() );
		Iterator<ICFBamValueObj> iterDelTableRefIndexColumns = arrDelTableRefIndexColumns.iterator();
		while( iterDelTableRefIndexColumns.hasNext() ) {
			objDelTableRefIndexColumns = iterDelTableRefIndexColumns.next();
			if( objDelTableRefIndexColumns != null ) {
						schema.getIndexColTableObj().deepDisposeIndexColByColIdx( objDelTableRefIndexColumns.getRequiredId() );
			}
		}
					schema.getValueTableObj().deepDisposeValueByScopeIdx( existing.getRequiredId() );

		if( indexBySchemaDefIdx != null ) {
			if( indexBySchemaDefIdx.containsKey( keySchemaDefIdx ) ) {
				indexBySchemaDefIdx.get( keySchemaDefIdx ).remove( pkey );
				if( indexBySchemaDefIdx.get( keySchemaDefIdx ).size() <= 0 ) {
					indexBySchemaDefIdx.remove( keySchemaDefIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexBySchemaCdIdx != null ) {
			indexBySchemaCdIdx.remove( keySchemaCdIdx );
		}

		if( indexByPrimaryIndexIdx != null ) {
			if( indexByPrimaryIndexIdx.containsKey( keyPrimaryIndexIdx ) ) {
				indexByPrimaryIndexIdx.get( keyPrimaryIndexIdx ).remove( pkey );
				if( indexByPrimaryIndexIdx.get( keyPrimaryIndexIdx ).size() <= 0 ) {
					indexByPrimaryIndexIdx.remove( keyPrimaryIndexIdx );
				}
			}
		}

		if( indexByLookupIndexIdx != null ) {
			if( indexByLookupIndexIdx.containsKey( keyLookupIndexIdx ) ) {
				indexByLookupIndexIdx.get( keyLookupIndexIdx ).remove( pkey );
				if( indexByLookupIndexIdx.get( keyLookupIndexIdx ).size() <= 0 ) {
					indexByLookupIndexIdx.remove( keyLookupIndexIdx );
				}
			}
		}

		if( indexByAltIndexIdx != null ) {
			if( indexByAltIndexIdx.containsKey( keyAltIndexIdx ) ) {
				indexByAltIndexIdx.get( keyAltIndexIdx ).remove( pkey );
				if( indexByAltIndexIdx.get( keyAltIndexIdx ).size() <= 0 ) {
					indexByAltIndexIdx.remove( keyAltIndexIdx );
				}
			}
		}

		if( indexByQualTableIdx != null ) {
			if( indexByQualTableIdx.containsKey( keyQualTableIdx ) ) {
				indexByQualTableIdx.get( keyQualTableIdx ).remove( pkey );
				if( indexByQualTableIdx.get( keyQualTableIdx ).size() <= 0 ) {
					indexByQualTableIdx.remove( keyQualTableIdx );
				}
			}
		}


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposeTable( CFLibDbKeyHash256 pkey ) {
		ICFBamTableObj obj = readCachedTable( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamTableObj lockTable( CFLibDbKeyHash256 pkey ) {
		ICFBamTableObj locked = null;
		ICFBamTable lockRec = schema.getCFBamBackingStore().getTableTable().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamTableObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockTable", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamTableObj> readAllTable() {
		return( readAllTable( false ) );
	}

	@Override
	public List<ICFBamTableObj> readAllTable( boolean forceRead ) {
		final String S_ProcName = "readAllTable";
		if( ( allTable == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> map = new HashMap<CFLibDbKeyHash256,ICFBamTableObj>();
			allTable = map;
			ICFBamTable[] recList = schema.getCFBamBackingStore().getTableTable().readAllDerived( null );
			ICFBamTable rec;
			ICFBamTableObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableObj realised = (ICFBamTableObj)obj.realise();
			}
		}
		int len = allTable.size();
		ICFBamTableObj arr[] = new ICFBamTableObj[len];
		Iterator<ICFBamTableObj> valIter = allTable.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableObj> readCachedAllTable() {
		final String S_ProcName = "readCachedAllTable";
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>();
		if( allTable != null ) {
			int len = allTable.size();
			ICFBamTableObj arr[] = new ICFBamTableObj[len];
			Iterator<ICFBamTableObj> valIter = allTable.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamTableObj readTableByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readTableByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamTableObj readTableByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamTableObj obj = readTable( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamTableObj> readTableByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readTableByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamTableObj> readTableByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableObj realised = (ICFBamTableObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableObj arr[] = new ICFBamTableObj[len];
		Iterator<ICFBamTableObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableObj> readTableBySchemaDefIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		return( readTableBySchemaDefIdx( SchemaDefId,
			false ) );
	}

	@Override
	public List<ICFBamTableObj> readTableBySchemaDefIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableBySchemaDefIdx";
		ICFBamTableBySchemaDefIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newBySchemaDefIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
		if( indexBySchemaDefIdx == null ) {
			indexBySchemaDefIdx = new HashMap< ICFBamTableBySchemaDefIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaDefIdx.containsKey( key ) ) {
			dict = indexBySchemaDefIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
			ICFBamTableObj obj;
			ICFBamTable[] recList = schema.getCFBamBackingStore().getTableTable().readDerivedBySchemaDefIdx( null,
				SchemaDefId );
			ICFBamTable rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableObj realised = (ICFBamTableObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaDefIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableObj arr[] = new ICFBamTableObj[len];
		Iterator<ICFBamTableObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableObj> readTableByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readTableByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamTableObj> readTableByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableByDefSchemaIdx";
		ICFBamTableByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTableByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
			ICFBamTableObj obj;
			ICFBamTable[] recList = schema.getCFBamBackingStore().getTableTable().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamTable rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableObj realised = (ICFBamTableObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableObj arr[] = new ICFBamTableObj[len];
		Iterator<ICFBamTableObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamTableObj readTableByUNameIdx( CFLibDbKeyHash256 SchemaDefId,
		String Name )
	{
		return( readTableByUNameIdx( SchemaDefId,
			Name,
			false ) );
	}

	@Override
	public ICFBamTableObj readTableByUNameIdx( CFLibDbKeyHash256 SchemaDefId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTableByUNameIdxKey,
				ICFBamTableObj >();
		}
		ICFBamTableByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByUNameIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		key.setRequiredName( Name );
		ICFBamTableObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamTable rec = schema.getCFBamBackingStore().getTableTable().readDerivedByUNameIdx( null,
				SchemaDefId,
				Name );
			if( rec != null ) {
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamTableObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamTableObj readTableBySchemaCdIdx( CFLibDbKeyHash256 SchemaDefId,
		String TableClassCode )
	{
		return( readTableBySchemaCdIdx( SchemaDefId,
			TableClassCode,
			false ) );
	}

	@Override
	public ICFBamTableObj readTableBySchemaCdIdx( CFLibDbKeyHash256 SchemaDefId,
		String TableClassCode, boolean forceRead )
	{
		if( indexBySchemaCdIdx == null ) {
			indexBySchemaCdIdx = new HashMap< ICFBamTableBySchemaCdIdxKey,
				ICFBamTableObj >();
		}
		ICFBamTableBySchemaCdIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newBySchemaCdIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		key.setRequiredTableClassCode( TableClassCode );
		ICFBamTableObj obj = null;
		if( ( ! forceRead ) && indexBySchemaCdIdx.containsKey( key ) ) {
			obj = indexBySchemaCdIdx.get( key );
		}
		else {
			ICFBamTable rec = schema.getCFBamBackingStore().getTableTable().readDerivedBySchemaCdIdx( null,
				SchemaDefId,
				TableClassCode );
			if( rec != null ) {
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamTableObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTableObj> readTableByPrimaryIndexIdx( CFLibDbKeyHash256 PrimaryIndexId )
	{
		return( readTableByPrimaryIndexIdx( PrimaryIndexId,
			false ) );
	}

	@Override
	public List<ICFBamTableObj> readTableByPrimaryIndexIdx( CFLibDbKeyHash256 PrimaryIndexId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableByPrimaryIndexIdx";
		ICFBamTableByPrimaryIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByPrimaryIndexIdxKey();
		key.setOptionalPrimaryIndexId( PrimaryIndexId );
		Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
		if( indexByPrimaryIndexIdx == null ) {
			indexByPrimaryIndexIdx = new HashMap< ICFBamTableByPrimaryIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( ( ! forceRead ) && indexByPrimaryIndexIdx.containsKey( key ) ) {
			dict = indexByPrimaryIndexIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
			ICFBamTableObj obj;
			ICFBamTable[] recList = schema.getCFBamBackingStore().getTableTable().readDerivedByPrimaryIndexIdx( null,
				PrimaryIndexId );
			ICFBamTable rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableObj realised = (ICFBamTableObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrimaryIndexIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableObj arr[] = new ICFBamTableObj[len];
		Iterator<ICFBamTableObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableObj> readTableByLookupIndexIdx( CFLibDbKeyHash256 LookupIndexId )
	{
		return( readTableByLookupIndexIdx( LookupIndexId,
			false ) );
	}

	@Override
	public List<ICFBamTableObj> readTableByLookupIndexIdx( CFLibDbKeyHash256 LookupIndexId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableByLookupIndexIdx";
		ICFBamTableByLookupIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByLookupIndexIdxKey();
		key.setOptionalLookupIndexId( LookupIndexId );
		Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
		if( indexByLookupIndexIdx == null ) {
			indexByLookupIndexIdx = new HashMap< ICFBamTableByLookupIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( ( ! forceRead ) && indexByLookupIndexIdx.containsKey( key ) ) {
			dict = indexByLookupIndexIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
			ICFBamTableObj obj;
			ICFBamTable[] recList = schema.getCFBamBackingStore().getTableTable().readDerivedByLookupIndexIdx( null,
				LookupIndexId );
			ICFBamTable rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableObj realised = (ICFBamTableObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByLookupIndexIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableObj arr[] = new ICFBamTableObj[len];
		Iterator<ICFBamTableObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableObj> readTableByAltIndexIdx( CFLibDbKeyHash256 AltIndexId )
	{
		return( readTableByAltIndexIdx( AltIndexId,
			false ) );
	}

	@Override
	public List<ICFBamTableObj> readTableByAltIndexIdx( CFLibDbKeyHash256 AltIndexId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableByAltIndexIdx";
		ICFBamTableByAltIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByAltIndexIdxKey();
		key.setOptionalAltIndexId( AltIndexId );
		Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
		if( indexByAltIndexIdx == null ) {
			indexByAltIndexIdx = new HashMap< ICFBamTableByAltIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( ( ! forceRead ) && indexByAltIndexIdx.containsKey( key ) ) {
			dict = indexByAltIndexIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
			ICFBamTableObj obj;
			ICFBamTable[] recList = schema.getCFBamBackingStore().getTableTable().readDerivedByAltIndexIdx( null,
				AltIndexId );
			ICFBamTable rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableObj realised = (ICFBamTableObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByAltIndexIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableObj arr[] = new ICFBamTableObj[len];
		Iterator<ICFBamTableObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableObj> readTableByQualTableIdx( CFLibDbKeyHash256 QualifyingTableId )
	{
		return( readTableByQualTableIdx( QualifyingTableId,
			false ) );
	}

	@Override
	public List<ICFBamTableObj> readTableByQualTableIdx( CFLibDbKeyHash256 QualifyingTableId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableByQualTableIdx";
		ICFBamTableByQualTableIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByQualTableIdxKey();
		key.setOptionalQualifyingTableId( QualifyingTableId );
		Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
		if( indexByQualTableIdx == null ) {
			indexByQualTableIdx = new HashMap< ICFBamTableByQualTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( ( ! forceRead ) && indexByQualTableIdx.containsKey( key ) ) {
			dict = indexByQualTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableObj>();
			ICFBamTableObj obj;
			ICFBamTable[] recList = schema.getCFBamBackingStore().getTableTable().readDerivedByQualTableIdx( null,
				QualifyingTableId );
			ICFBamTable rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableObj realised = (ICFBamTableObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByQualTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableObj arr[] = new ICFBamTableObj[len];
		Iterator<ICFBamTableObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamTableObj readCachedTableByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTableObj obj = null;
		obj = readCachedTable( Id );
		return( obj );
	}

	@Override
	public List<ICFBamTableObj> readCachedTableByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedTableByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamTableObj arr[] = new ICFBamTableObj[len];
				Iterator<ICFBamTableObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableObj obj;
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTableObj> readCachedTableBySchemaDefIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "readCachedTableBySchemaDefIdx";
		ICFBamTableBySchemaDefIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newBySchemaDefIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>();
		if( indexBySchemaDefIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
			if( indexBySchemaDefIdx.containsKey( key ) ) {
				dict = indexBySchemaDefIdx.get( key );
				int len = dict.size();
				ICFBamTableObj arr[] = new ICFBamTableObj[len];
				Iterator<ICFBamTableObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableObj obj;
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTableObj> readCachedTableByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedTableByDefSchemaIdx";
		ICFBamTableByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamTableObj arr[] = new ICFBamTableObj[len];
				Iterator<ICFBamTableObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableObj obj;
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamTableObj readCachedTableByUNameIdx( CFLibDbKeyHash256 SchemaDefId,
		String Name )
	{
		ICFBamTableObj obj = null;
		ICFBamTableByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByUNameIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamTableObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public ICFBamTableObj readCachedTableBySchemaCdIdx( CFLibDbKeyHash256 SchemaDefId,
		String TableClassCode )
	{
		ICFBamTableObj obj = null;
		ICFBamTableBySchemaCdIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newBySchemaCdIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		key.setRequiredTableClassCode( TableClassCode );
		if( indexBySchemaCdIdx != null ) {
			if( indexBySchemaCdIdx.containsKey( key ) ) {
				obj = indexBySchemaCdIdx.get( key );
			}
			else {
				Iterator<ICFBamTableObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTableObj> readCachedTableByPrimaryIndexIdx( CFLibDbKeyHash256 PrimaryIndexId )
	{
		final String S_ProcName = "readCachedTableByPrimaryIndexIdx";
		ICFBamTableByPrimaryIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByPrimaryIndexIdxKey();
		key.setOptionalPrimaryIndexId( PrimaryIndexId );
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>();
		if( indexByPrimaryIndexIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
			if( indexByPrimaryIndexIdx.containsKey( key ) ) {
				dict = indexByPrimaryIndexIdx.get( key );
				int len = dict.size();
				ICFBamTableObj arr[] = new ICFBamTableObj[len];
				Iterator<ICFBamTableObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableObj obj;
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTableObj> readCachedTableByLookupIndexIdx( CFLibDbKeyHash256 LookupIndexId )
	{
		final String S_ProcName = "readCachedTableByLookupIndexIdx";
		ICFBamTableByLookupIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByLookupIndexIdxKey();
		key.setOptionalLookupIndexId( LookupIndexId );
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>();
		if( indexByLookupIndexIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
			if( indexByLookupIndexIdx.containsKey( key ) ) {
				dict = indexByLookupIndexIdx.get( key );
				int len = dict.size();
				ICFBamTableObj arr[] = new ICFBamTableObj[len];
				Iterator<ICFBamTableObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableObj obj;
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTableObj> readCachedTableByAltIndexIdx( CFLibDbKeyHash256 AltIndexId )
	{
		final String S_ProcName = "readCachedTableByAltIndexIdx";
		ICFBamTableByAltIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByAltIndexIdxKey();
		key.setOptionalAltIndexId( AltIndexId );
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>();
		if( indexByAltIndexIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
			if( indexByAltIndexIdx.containsKey( key ) ) {
				dict = indexByAltIndexIdx.get( key );
				int len = dict.size();
				ICFBamTableObj arr[] = new ICFBamTableObj[len];
				Iterator<ICFBamTableObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableObj obj;
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTableObj> readCachedTableByQualTableIdx( CFLibDbKeyHash256 QualifyingTableId )
	{
		final String S_ProcName = "readCachedTableByQualTableIdx";
		ICFBamTableByQualTableIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByQualTableIdxKey();
		key.setOptionalQualifyingTableId( QualifyingTableId );
		ArrayList<ICFBamTableObj> arrayList = new ArrayList<ICFBamTableObj>();
		if( indexByQualTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict;
			if( indexByQualTableIdx.containsKey( key ) ) {
				dict = indexByQualTableIdx.get( key );
				int len = dict.size();
				ICFBamTableObj arr[] = new ICFBamTableObj[len];
				Iterator<ICFBamTableObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableObj obj;
			Iterator<ICFBamTableObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableObj> cmp = new Comparator<ICFBamTableObj>() {
			@Override
			public int compare( ICFBamTableObj lhs, ICFBamTableObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeTableByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTableObj obj = readCachedTableByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeTableByTenantIdx";
		ICFBamTableObj obj;
		List<ICFBamTableObj> arrayList = readCachedTableByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamTableObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableBySchemaDefIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "deepDisposeTableBySchemaDefIdx";
		ICFBamTableObj obj;
		List<ICFBamTableObj> arrayList = readCachedTableBySchemaDefIdx( SchemaDefId );
		if( arrayList != null )  {
			Iterator<ICFBamTableObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeTableByDefSchemaIdx";
		ICFBamTableObj obj;
		List<ICFBamTableObj> arrayList = readCachedTableByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamTableObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableByUNameIdx( CFLibDbKeyHash256 SchemaDefId,
		String Name )
	{
		ICFBamTableObj obj = readCachedTableByUNameIdx( SchemaDefId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableBySchemaCdIdx( CFLibDbKeyHash256 SchemaDefId,
		String TableClassCode )
	{
		ICFBamTableObj obj = readCachedTableBySchemaCdIdx( SchemaDefId,
				TableClassCode );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableByPrimaryIndexIdx( CFLibDbKeyHash256 PrimaryIndexId )
	{
		final String S_ProcName = "deepDisposeTableByPrimaryIndexIdx";
		ICFBamTableObj obj;
		List<ICFBamTableObj> arrayList = readCachedTableByPrimaryIndexIdx( PrimaryIndexId );
		if( arrayList != null )  {
			Iterator<ICFBamTableObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableByLookupIndexIdx( CFLibDbKeyHash256 LookupIndexId )
	{
		final String S_ProcName = "deepDisposeTableByLookupIndexIdx";
		ICFBamTableObj obj;
		List<ICFBamTableObj> arrayList = readCachedTableByLookupIndexIdx( LookupIndexId );
		if( arrayList != null )  {
			Iterator<ICFBamTableObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableByAltIndexIdx( CFLibDbKeyHash256 AltIndexId )
	{
		final String S_ProcName = "deepDisposeTableByAltIndexIdx";
		ICFBamTableObj obj;
		List<ICFBamTableObj> arrayList = readCachedTableByAltIndexIdx( AltIndexId );
		if( arrayList != null )  {
			Iterator<ICFBamTableObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableByQualTableIdx( CFLibDbKeyHash256 QualifyingTableId )
	{
		final String S_ProcName = "deepDisposeTableByQualTableIdx";
		ICFBamTableObj obj;
		List<ICFBamTableObj> arrayList = readCachedTableByQualTableIdx( QualifyingTableId );
		if( arrayList != null )  {
			Iterator<ICFBamTableObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamTableObj updateTable( ICFBamTableObj Obj ) {
		ICFBamTableObj obj = Obj;
		schema.getCFBamBackingStore().getTableTable().updateTable( null,
			Obj.getTableRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getTableTableObj().getClassCode() ) {
			obj = (ICFBamTableObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteTable( ICFBamTableObj Obj ) {
		ICFBamTableObj obj = Obj;
		schema.getCFBamBackingStore().getTableTable().deleteTable( null,
			obj.getTableRec() );
		Obj.forget();
	}

	@Override
	public void deleteTableByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTableObj obj = readTable(Id);
		if( obj != null ) {
			ICFBamTableEditObj editObj = (ICFBamTableEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamTableEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeTableByIdIdx( Id );
	}

	@Override
	public void deleteTableByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableByTenantIdx( null,
				TenantId );
			Iterator<ICFBamTableObj> iter = dict.values().iterator();
			ICFBamTableObj obj;
			List<ICFBamTableObj> toForget = new LinkedList<ICFBamTableObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableByTenantIdx( null,
				TenantId );
		}
		deepDisposeTableByTenantIdx( TenantId );
	}

	@Override
	public void deleteTableBySchemaDefIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		ICFBamTableBySchemaDefIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newBySchemaDefIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		if( indexBySchemaDefIdx == null ) {
			indexBySchemaDefIdx = new HashMap< ICFBamTableBySchemaDefIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( indexBySchemaDefIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict = indexBySchemaDefIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableBySchemaDefIdx( null,
				SchemaDefId );
			Iterator<ICFBamTableObj> iter = dict.values().iterator();
			ICFBamTableObj obj;
			List<ICFBamTableObj> toForget = new LinkedList<ICFBamTableObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySchemaDefIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableBySchemaDefIdx( null,
				SchemaDefId );
		}
		deepDisposeTableBySchemaDefIdx( SchemaDefId );
	}

	@Override
	public void deleteTableByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamTableByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTableByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamTableObj> iter = dict.values().iterator();
			ICFBamTableObj obj;
			List<ICFBamTableObj> toForget = new LinkedList<ICFBamTableObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeTableByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteTableByUNameIdx( CFLibDbKeyHash256 SchemaDefId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTableByUNameIdxKey,
				ICFBamTableObj >();
		}
		ICFBamTableByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByUNameIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		key.setRequiredName( Name );
		ICFBamTableObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableByUNameIdx( null,
				SchemaDefId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableByUNameIdx( null,
				SchemaDefId,
				Name );
		}
		deepDisposeTableByUNameIdx( SchemaDefId,
				Name );
	}

	@Override
	public void deleteTableBySchemaCdIdx( CFLibDbKeyHash256 SchemaDefId,
		String TableClassCode )
	{
		if( indexBySchemaCdIdx == null ) {
			indexBySchemaCdIdx = new HashMap< ICFBamTableBySchemaCdIdxKey,
				ICFBamTableObj >();
		}
		ICFBamTableBySchemaCdIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newBySchemaCdIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		key.setRequiredTableClassCode( TableClassCode );
		ICFBamTableObj obj = null;
		if( indexBySchemaCdIdx.containsKey( key ) ) {
			obj = indexBySchemaCdIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableBySchemaCdIdx( null,
				SchemaDefId,
				TableClassCode );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableBySchemaCdIdx( null,
				SchemaDefId,
				TableClassCode );
		}
		deepDisposeTableBySchemaCdIdx( SchemaDefId,
				TableClassCode );
	}

	@Override
	public void deleteTableByPrimaryIndexIdx( CFLibDbKeyHash256 PrimaryIndexId )
	{
		ICFBamTableByPrimaryIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByPrimaryIndexIdxKey();
		key.setOptionalPrimaryIndexId( PrimaryIndexId );
		if( indexByPrimaryIndexIdx == null ) {
			indexByPrimaryIndexIdx = new HashMap< ICFBamTableByPrimaryIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( indexByPrimaryIndexIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict = indexByPrimaryIndexIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableByPrimaryIndexIdx( null,
				PrimaryIndexId );
			Iterator<ICFBamTableObj> iter = dict.values().iterator();
			ICFBamTableObj obj;
			List<ICFBamTableObj> toForget = new LinkedList<ICFBamTableObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrimaryIndexIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableByPrimaryIndexIdx( null,
				PrimaryIndexId );
		}
		deepDisposeTableByPrimaryIndexIdx( PrimaryIndexId );
	}

	@Override
	public void deleteTableByLookupIndexIdx( CFLibDbKeyHash256 LookupIndexId )
	{
		ICFBamTableByLookupIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByLookupIndexIdxKey();
		key.setOptionalLookupIndexId( LookupIndexId );
		if( indexByLookupIndexIdx == null ) {
			indexByLookupIndexIdx = new HashMap< ICFBamTableByLookupIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( indexByLookupIndexIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict = indexByLookupIndexIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableByLookupIndexIdx( null,
				LookupIndexId );
			Iterator<ICFBamTableObj> iter = dict.values().iterator();
			ICFBamTableObj obj;
			List<ICFBamTableObj> toForget = new LinkedList<ICFBamTableObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByLookupIndexIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableByLookupIndexIdx( null,
				LookupIndexId );
		}
		deepDisposeTableByLookupIndexIdx( LookupIndexId );
	}

	@Override
	public void deleteTableByAltIndexIdx( CFLibDbKeyHash256 AltIndexId )
	{
		ICFBamTableByAltIndexIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByAltIndexIdxKey();
		key.setOptionalAltIndexId( AltIndexId );
		if( indexByAltIndexIdx == null ) {
			indexByAltIndexIdx = new HashMap< ICFBamTableByAltIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( indexByAltIndexIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict = indexByAltIndexIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableByAltIndexIdx( null,
				AltIndexId );
			Iterator<ICFBamTableObj> iter = dict.values().iterator();
			ICFBamTableObj obj;
			List<ICFBamTableObj> toForget = new LinkedList<ICFBamTableObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByAltIndexIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableByAltIndexIdx( null,
				AltIndexId );
		}
		deepDisposeTableByAltIndexIdx( AltIndexId );
	}

	@Override
	public void deleteTableByQualTableIdx( CFLibDbKeyHash256 QualifyingTableId )
	{
		ICFBamTableByQualTableIdxKey key = schema.getCFBamBackingStore().getFactoryTable().newByQualTableIdxKey();
		key.setOptionalQualifyingTableId( QualifyingTableId );
		if( indexByQualTableIdx == null ) {
			indexByQualTableIdx = new HashMap< ICFBamTableByQualTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableObj > >();
		}
		if( indexByQualTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableObj> dict = indexByQualTableIdx.get( key );
			schema.getCFBamBackingStore().getTableTable().deleteTableByQualTableIdx( null,
				QualifyingTableId );
			Iterator<ICFBamTableObj> iter = dict.values().iterator();
			ICFBamTableObj obj;
			List<ICFBamTableObj> toForget = new LinkedList<ICFBamTableObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByQualTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTable().deleteTableByQualTableIdx( null,
				QualifyingTableId );
		}
		deepDisposeTableByQualTableIdx( QualifyingTableId );
	}
}