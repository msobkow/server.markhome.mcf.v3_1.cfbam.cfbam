// Description: Java 25 Table Object implementation for TimeType.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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

public class CFBamTimeTypeTableObj
	implements ICFBamTimeTypeTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamTimeType.CLASS_CODE;
	protected static final int backingClassCode = ICFBamTimeType.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> members;
	private Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> allTimeType;
	private Map< ICFBamValueByUNameIdxKey,
		ICFBamTimeTypeObj > indexByUNameIdx;
	private Map< ICFBamValueByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > > indexByScopeIdx;
	private Map< ICFBamValueByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > > indexByDefSchemaIdx;
	private Map< ICFBamValueByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > > indexByPrevIdx;
	private Map< ICFBamValueByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > > indexByNextIdx;
	private Map< ICFBamValueByContPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > > indexByContPrevIdx;
	private Map< ICFBamValueByContNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > > indexByContNextIdx;
	private Map< ICFBamTimeTypeBySchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > > indexBySchemaIdx;
	public static String TABLE_NAME = "TimeType";
	public static String TABLE_DBNAME = "tmtyp";

	public CFBamTimeTypeTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
		allTimeType = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
	}

	public CFBamTimeTypeTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
		allTimeType = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamTimeTypeTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamTimeTypeTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allTimeType = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
		List<ICFBamTimeTypeObj> toForget = new LinkedList<ICFBamTimeTypeObj>();
		ICFBamTimeTypeObj cur = null;
		Iterator<ICFBamTimeTypeObj> iter = members.values().iterator();
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
	 *	CFBamTimeTypeObj.
	 */
	@Override
	public ICFBamTimeTypeObj newInstance() {
		ICFBamTimeTypeObj inst = new CFBamTimeTypeObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamTimeTypeObj.
	 */
	@Override
	public ICFBamTimeTypeEditObj newEditInstance( ICFBamTimeTypeObj orig ) {
		ICFBamTimeTypeEditObj edit = new CFBamTimeTypeEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamTimeTypeObj realiseTimeType( ICFBamTimeTypeObj Obj ) {
		ICFBamTimeTypeObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTimeTypeObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamTimeTypeObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamTimeTypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryTimeType().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.remove( keepObj.getPKey() );
					if( mapSchemaIdx.size() <= 0 ) {
						indexBySchemaIdx.remove( keySchemaIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamTimeTypeObj)schema.getTimeDefTableObj().realiseTimeDef( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamTimeTypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryTimeType().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allTimeType != null ) {
				allTimeType.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamTimeTypeObj)schema.getTimeDefTableObj().realiseTimeDef( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allTimeType != null ) {
				allTimeType.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamTimeTypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryTimeType().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamTimeTypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamTimeTypeObj createTimeType( ICFBamTimeTypeObj Obj ) {
		ICFBamTimeTypeObj obj = Obj;
		ICFBamTimeType rec = obj.getTimeTypeRec();
		schema.getCFBamBackingStore().getTableTimeType().createTimeType(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamTimeTypeObj)(obj.realise());
		}
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamTimeTypeObj readTimeType( CFLibDbKeyHash256 pkey ) {
		return( readTimeType( pkey, false ) );
	}

	@Override
	public ICFBamTimeTypeObj readTimeType( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamTimeTypeObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamTimeType readRec = schema.getCFBamBackingStore().getTableTimeType().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamTimeTypeObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamTimeTypeObj readCachedTimeType( CFLibDbKeyHash256 pkey ) {
		ICFBamTimeTypeObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeTimeType( ICFBamTimeTypeObj obj )
	{
		final String S_ProcName = "CFBamTimeTypeTableObj.reallyDeepDisposeTimeType() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTimeTypeObj existing = readCachedTimeType( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamTimeTypeBySchemaIdxKey keySchemaIdx = schema.getCFBamBackingStore().getFactoryTimeType().newBySchemaIdxKey();
		keySchemaIdx.setRequiredSchemaDefId( existing.getRequiredSchemaDefId() );


		schema.getTableColTableObj().deepDisposeTableColByDataIdx( existing.getRequiredId() );
		schema.getIndexColTableObj().deepDisposeIndexColByColIdx( existing.getRequiredId() );

		if( indexBySchemaIdx != null ) {
			if( indexBySchemaIdx.containsKey( keySchemaIdx ) ) {
				indexBySchemaIdx.get( keySchemaIdx ).remove( pkey );
				if( indexBySchemaIdx.get( keySchemaIdx ).size() <= 0 ) {
					indexBySchemaIdx.remove( keySchemaIdx );
				}
			}
		}


		schema.getTimeDefTableObj().reallyDeepDisposeTimeDef( obj );
	}
	@Override
	public void deepDisposeTimeType( CFLibDbKeyHash256 pkey ) {
		ICFBamTimeTypeObj obj = readCachedTimeType( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamTimeTypeObj lockTimeType( CFLibDbKeyHash256 pkey ) {
		ICFBamTimeTypeObj locked = null;
		ICFBamTimeType lockRec = schema.getCFBamBackingStore().getTableTimeType().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamTimeTypeObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockTimeType", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamTimeTypeObj> readAllTimeType() {
		return( readAllTimeType( false ) );
	}

	@Override
	public List<ICFBamTimeTypeObj> readAllTimeType( boolean forceRead ) {
		final String S_ProcName = "readAllTimeType";
		if( ( allTimeType == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> map = new HashMap<CFLibDbKeyHash256,ICFBamTimeTypeObj>();
			allTimeType = map;
			ICFBamTimeType[] recList = schema.getCFBamBackingStore().getTableTimeType().readAllDerived( null );
			ICFBamTimeType rec;
			ICFBamTimeTypeObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimeTypeObj realised = (ICFBamTimeTypeObj)obj.realise();
			}
		}
		int len = allTimeType.size();
		ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
		Iterator<ICFBamTimeTypeObj> valIter = allTimeType.values().iterator();
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
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
		List<ICFBamTimeTypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimeTypeObj> readCachedAllTimeType() {
		final String S_ProcName = "readCachedAllTimeType";
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>();
		if( allTimeType != null ) {
			int len = allTimeType.size();
			ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
			Iterator<ICFBamTimeTypeObj> valIter = allTimeType.values().iterator();
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
		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
	public ICFBamTimeTypeObj readTimeTypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readTimeTypeByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamTimeTypeObj readTimeTypeByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamTimeTypeObj obj = readTimeType( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamTimeTypeObj readTimeTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readTimeTypeByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamTimeTypeObj readTimeTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamTimeTypeObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamTimeTypeObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamTimeTypeObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readTimeTypeByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimeTypeByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimeTypeObj realised = (ICFBamTimeTypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
		Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
		List<ICFBamTimeTypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readTimeTypeByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimeTypeByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimeTypeObj realised = (ICFBamTimeTypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
		Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
		List<ICFBamTimeTypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readTimeTypeByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimeTypeByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimeTypeObj realised = (ICFBamTimeTypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
		Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
		List<ICFBamTimeTypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readTimeTypeByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimeTypeByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByNextIdx( null,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimeTypeObj realised = (ICFBamTimeTypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
		Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
		List<ICFBamTimeTypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readTimeTypeByContPrevIdx( ScopeId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimeTypeByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContPrevIdx( null,
				ScopeId,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimeTypeObj realised = (ICFBamTimeTypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
		Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
		List<ICFBamTimeTypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		return( readTimeTypeByContNextIdx( ScopeId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimeTypeByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContNextIdx( null,
				ScopeId,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimeTypeObj realised = (ICFBamTimeTypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
		Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
		List<ICFBamTimeTypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		return( readTimeTypeBySchemaIdx( SchemaDefId,
			false ) );
	}

	@Override
	public List<ICFBamTimeTypeObj> readTimeTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimeTypeBySchemaIdx";
		ICFBamTimeTypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTimeType().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamTimeTypeBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaIdx.containsKey( key ) ) {
			dict = indexBySchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimeTypeObj>();
			ICFBamTimeTypeObj obj;
			ICFBamTimeType[] recList = schema.getCFBamBackingStore().getTableTimeType().readDerivedBySchemaIdx( null,
				SchemaDefId );
			ICFBamTimeType rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimeTypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimeTypeObj realised = (ICFBamTimeTypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
		Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
		List<ICFBamTimeTypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamTimeTypeObj readCachedTimeTypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTimeTypeObj obj = null;
		obj = readCachedTimeType( Id );
		return( obj );
	}

	@Override
	public ICFBamTimeTypeObj readCachedTimeTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamTimeTypeObj obj = null;
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
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
			Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
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
	public List<ICFBamTimeTypeObj> readCachedTimeTypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedTimeTypeByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
				Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
			ICFBamTimeTypeObj obj;
			Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
	public List<ICFBamTimeTypeObj> readCachedTimeTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedTimeTypeByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
				Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
			ICFBamTimeTypeObj obj;
			Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
	public List<ICFBamTimeTypeObj> readCachedTimeTypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedTimeTypeByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
				Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
			ICFBamTimeTypeObj obj;
			Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
	public List<ICFBamTimeTypeObj> readCachedTimeTypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedTimeTypeByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
				Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
			ICFBamTimeTypeObj obj;
			Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
	public List<ICFBamTimeTypeObj> readCachedTimeTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedTimeTypeByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>();
		if( indexByContPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
				Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
			ICFBamTimeTypeObj obj;
			Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
	public List<ICFBamTimeTypeObj> readCachedTimeTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedTimeTypeByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>();
		if( indexByContNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
				Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
			ICFBamTimeTypeObj obj;
			Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
	public List<ICFBamTimeTypeObj> readCachedTimeTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "readCachedTimeTypeBySchemaIdx";
		ICFBamTimeTypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTimeType().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		ArrayList<ICFBamTimeTypeObj> arrayList = new ArrayList<ICFBamTimeTypeObj>();
		if( indexBySchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict;
			if( indexBySchemaIdx.containsKey( key ) ) {
				dict = indexBySchemaIdx.get( key );
				int len = dict.size();
				ICFBamTimeTypeObj arr[] = new ICFBamTimeTypeObj[len];
				Iterator<ICFBamTimeTypeObj> valIter = dict.values().iterator();
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
			ICFBamTimeTypeObj obj;
			Iterator<ICFBamTimeTypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimeTypeObj> cmp = new Comparator<ICFBamTimeTypeObj>() {
			@Override
			public int compare( ICFBamTimeTypeObj lhs, ICFBamTimeTypeObj rhs ) {
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
	public void deepDisposeTimeTypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTimeTypeObj obj = readCachedTimeTypeByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTimeTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamTimeTypeObj obj = readCachedTimeTypeByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTimeTypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeTimeTypeByScopeIdx";
		ICFBamTimeTypeObj obj;
		List<ICFBamTimeTypeObj> arrayList = readCachedTimeTypeByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamTimeTypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimeTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeTimeTypeByDefSchemaIdx";
		ICFBamTimeTypeObj obj;
		List<ICFBamTimeTypeObj> arrayList = readCachedTimeTypeByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamTimeTypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimeTypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeTimeTypeByPrevIdx";
		ICFBamTimeTypeObj obj;
		List<ICFBamTimeTypeObj> arrayList = readCachedTimeTypeByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamTimeTypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimeTypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeTimeTypeByNextIdx";
		ICFBamTimeTypeObj obj;
		List<ICFBamTimeTypeObj> arrayList = readCachedTimeTypeByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamTimeTypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimeTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeTimeTypeByContPrevIdx";
		ICFBamTimeTypeObj obj;
		List<ICFBamTimeTypeObj> arrayList = readCachedTimeTypeByContPrevIdx( ScopeId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamTimeTypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimeTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeTimeTypeByContNextIdx";
		ICFBamTimeTypeObj obj;
		List<ICFBamTimeTypeObj> arrayList = readCachedTimeTypeByContNextIdx( ScopeId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamTimeTypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimeTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "deepDisposeTimeTypeBySchemaIdx";
		ICFBamTimeTypeObj obj;
		List<ICFBamTimeTypeObj> arrayList = readCachedTimeTypeBySchemaIdx( SchemaDefId );
		if( arrayList != null )  {
			Iterator<ICFBamTimeTypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamTimeTypeObj updateTimeType( ICFBamTimeTypeObj Obj ) {
		ICFBamTimeTypeObj obj = Obj;
		schema.getCFBamBackingStore().getTableTimeType().updateTimeType( null,
			Obj.getTimeTypeRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getTimeTypeTableObj().getClassCode() ) {
			obj = (ICFBamTimeTypeObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteTimeType( ICFBamTimeTypeObj Obj ) {
		ICFBamTimeTypeObj obj = Obj;
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		ICFBamValueObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableTimeType().deleteTimeType( null,
			obj.getTimeTypeRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteTimeTypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTimeTypeObj obj = readTimeType(Id);
		if( obj != null ) {
			ICFBamTimeTypeEditObj editObj = (ICFBamTimeTypeEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamTimeTypeEditObj)obj.beginEdit();
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
		deepDisposeTimeTypeByIdIdx( Id );
	}

	@Override
	public void deleteTimeTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamTimeTypeObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamTimeTypeObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeTimeTypeByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteTimeTypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamTimeTypeObj> iter = dict.values().iterator();
			ICFBamTimeTypeObj obj;
			List<ICFBamTimeTypeObj> toForget = new LinkedList<ICFBamTimeTypeObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByScopeIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByScopeIdx( null,
				ScopeId );
		}
		deepDisposeTimeTypeByScopeIdx( ScopeId );
	}

	@Override
	public void deleteTimeTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamTimeTypeObj> iter = dict.values().iterator();
			ICFBamTimeTypeObj obj;
			List<ICFBamTimeTypeObj> toForget = new LinkedList<ICFBamTimeTypeObj>();
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
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeTimeTypeByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteTimeTypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByPrevIdx( null,
				PrevId );
			Iterator<ICFBamTimeTypeObj> iter = dict.values().iterator();
			ICFBamTimeTypeObj obj;
			List<ICFBamTimeTypeObj> toForget = new LinkedList<ICFBamTimeTypeObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByPrevIdx( null,
				PrevId );
		}
		deepDisposeTimeTypeByPrevIdx( PrevId );
	}

	@Override
	public void deleteTimeTypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByNextIdx( null,
				NextId );
			Iterator<ICFBamTimeTypeObj> iter = dict.values().iterator();
			ICFBamTimeTypeObj obj;
			List<ICFBamTimeTypeObj> toForget = new LinkedList<ICFBamTimeTypeObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByNextIdx( null,
				NextId );
		}
		deepDisposeTimeTypeByNextIdx( NextId );
	}

	@Override
	public void deleteTimeTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByContPrevIdx( null,
				ScopeId,
				PrevId );
			Iterator<ICFBamTimeTypeObj> iter = dict.values().iterator();
			ICFBamTimeTypeObj obj;
			List<ICFBamTimeTypeObj> toForget = new LinkedList<ICFBamTimeTypeObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByContPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByContPrevIdx( null,
				ScopeId,
				PrevId );
		}
		deepDisposeTimeTypeByContPrevIdx( ScopeId,
				PrevId );
	}

	@Override
	public void deleteTimeTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByContNextIdx( null,
				ScopeId,
				NextId );
			Iterator<ICFBamTimeTypeObj> iter = dict.values().iterator();
			ICFBamTimeTypeObj obj;
			List<ICFBamTimeTypeObj> toForget = new LinkedList<ICFBamTimeTypeObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByContNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeByContNextIdx( null,
				ScopeId,
				NextId );
		}
		deepDisposeTimeTypeByContNextIdx( ScopeId,
				NextId );
	}

	@Override
	public void deleteTimeTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		ICFBamTimeTypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTimeType().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamTimeTypeBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimeTypeObj > >();
		}
		if( indexBySchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimeTypeObj> dict = indexBySchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeBySchemaIdx( null,
				SchemaDefId );
			Iterator<ICFBamTimeTypeObj> iter = dict.values().iterator();
			ICFBamTimeTypeObj obj;
			List<ICFBamTimeTypeObj> toForget = new LinkedList<ICFBamTimeTypeObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTimeType().deleteTimeTypeBySchemaIdx( null,
				SchemaDefId );
		}
		deepDisposeTimeTypeBySchemaIdx( SchemaDefId );
	}

	/**
	 *	Move the CFBamTimeTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimeTypeObj refreshed cache instance.
	 */
	@Override
	public ICFBamTimeTypeObj moveUpTimeType( ICFBamTimeTypeObj Obj ) {
		ICFBamTimeTypeObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpTimeType" );
		}
		ICFBamTimeType rec = schema.getCFBamBackingStore().getTableTimeType().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getTimeTypeTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamTimeTypeObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamValueObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamTimeTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimeTypeObj refreshed cache instance.
	 */
	@Override
	public ICFBamTimeTypeObj moveDownTimeType( ICFBamTimeTypeObj Obj ) {
		ICFBamTimeTypeObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownTimeType" );
		}
		ICFBamTimeType rec = schema.getCFBamBackingStore().getTableTimeType().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getTimeTypeTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamTimeTypeObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamValueObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}