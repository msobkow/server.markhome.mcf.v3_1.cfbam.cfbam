// Description: Java 25 Table Object implementation for TimestampCol.

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

public class CFBamTimestampColTableObj
	implements ICFBamTimestampColTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamTimestampCol.CLASS_CODE;
	protected static final int backingClassCode = ICFBamTimestampCol.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamTimestampColObj> members;
	private Map<CFLibDbKeyHash256, ICFBamTimestampColObj> allTimestampCol;
	private Map< ICFBamValueByUNameIdxKey,
		ICFBamTimestampColObj > indexByUNameIdx;
	private Map< ICFBamValueByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj > > indexByScopeIdx;
	private Map< ICFBamValueByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj > > indexByDefSchemaIdx;
	private Map< ICFBamValueByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj > > indexByPrevIdx;
	private Map< ICFBamValueByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj > > indexByNextIdx;
	private Map< ICFBamValueByContPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj > > indexByContPrevIdx;
	private Map< ICFBamValueByContNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj > > indexByContNextIdx;
	private Map< ICFBamTimestampColByTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj > > indexByTableIdx;
	public static String TABLE_NAME = "TimestampCol";
	public static String TABLE_DBNAME = "tscol";

	public CFBamTimestampColTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
		allTimestampCol = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexByTableIdx = null;
	}

	public CFBamTimestampColTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
		allTimestampCol = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexByTableIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamTimestampColTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamTimestampColTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allTimestampCol = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexByTableIdx = null;
		List<ICFBamTimestampColObj> toForget = new LinkedList<ICFBamTimestampColObj>();
		ICFBamTimestampColObj cur = null;
		Iterator<ICFBamTimestampColObj> iter = members.values().iterator();
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
	 *	CFBamTimestampColObj.
	 */
	@Override
	public ICFBamTimestampColObj newInstance() {
		ICFBamTimestampColObj inst = new CFBamTimestampColObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamTimestampColObj.
	 */
	@Override
	public ICFBamTimestampColEditObj newEditInstance( ICFBamTimestampColObj orig ) {
		ICFBamTimestampColEditObj edit = new CFBamTimestampColEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamTimestampColObj realiseTimestampCol( ICFBamTimestampColObj Obj ) {
		ICFBamTimestampColObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTimestampColObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamTimestampColObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamTimestampColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryTimestampCol().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.remove( keepObj.getPKey() );
					if( mapTableIdx.size() <= 0 ) {
						indexByTableIdx.remove( keyTableIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamTimestampColObj)schema.getTimestampDefTableObj().realiseTimestampDef( Obj );

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
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamTimestampColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryTimestampCol().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allTimestampCol != null ) {
				allTimestampCol.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamTimestampColObj)schema.getTimestampDefTableObj().realiseTimestampDef( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allTimestampCol != null ) {
				allTimestampCol.put( keepObj.getPKey(), keepObj );
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
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamTimestampColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryTimestampCol().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamTimestampColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamTimestampColObj createTimestampCol( ICFBamTimestampColObj Obj ) {
		ICFBamTimestampColObj obj = Obj;
		ICFBamTimestampCol rec = obj.getTimestampColRec();
		schema.getCFBamBackingStore().getTableTimestampCol().createTimestampCol(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamTimestampColObj)(obj.realise());
		}
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamTimestampColObj readTimestampCol( CFLibDbKeyHash256 pkey ) {
		return( readTimestampCol( pkey, false ) );
	}

	@Override
	public ICFBamTimestampColObj readTimestampCol( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamTimestampColObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamTimestampCol readRec = schema.getCFBamBackingStore().getTableTimestampCol().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamTimestampColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamTimestampColObj readCachedTimestampCol( CFLibDbKeyHash256 pkey ) {
		ICFBamTimestampColObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeTimestampCol( ICFBamTimestampColObj obj )
	{
		final String S_ProcName = "CFBamTimestampColTableObj.reallyDeepDisposeTimestampCol() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTimestampColObj existing = readCachedTimestampCol( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamTimestampColByTableIdxKey keyTableIdx = schema.getCFBamBackingStore().getFactoryTimestampCol().newByTableIdxKey();
		keyTableIdx.setRequiredTableId( existing.getRequiredTableId() );


		schema.getTableColTableObj().deepDisposeTableColByDataIdx( existing.getRequiredId() );
		schema.getIndexColTableObj().deepDisposeIndexColByColIdx( existing.getRequiredId() );

		if( indexByTableIdx != null ) {
			if( indexByTableIdx.containsKey( keyTableIdx ) ) {
				indexByTableIdx.get( keyTableIdx ).remove( pkey );
				if( indexByTableIdx.get( keyTableIdx ).size() <= 0 ) {
					indexByTableIdx.remove( keyTableIdx );
				}
			}
		}


		schema.getTimestampDefTableObj().reallyDeepDisposeTimestampDef( obj );
	}
	@Override
	public void deepDisposeTimestampCol( CFLibDbKeyHash256 pkey ) {
		ICFBamTimestampColObj obj = readCachedTimestampCol( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamTimestampColObj lockTimestampCol( CFLibDbKeyHash256 pkey ) {
		ICFBamTimestampColObj locked = null;
		ICFBamTimestampCol lockRec = schema.getCFBamBackingStore().getTableTimestampCol().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamTimestampColObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockTimestampCol", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamTimestampColObj> readAllTimestampCol() {
		return( readAllTimestampCol( false ) );
	}

	@Override
	public List<ICFBamTimestampColObj> readAllTimestampCol( boolean forceRead ) {
		final String S_ProcName = "readAllTimestampCol";
		if( ( allTimestampCol == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> map = new HashMap<CFLibDbKeyHash256,ICFBamTimestampColObj>();
			allTimestampCol = map;
			ICFBamTimestampCol[] recList = schema.getCFBamBackingStore().getTableTimestampCol().readAllDerived( null );
			ICFBamTimestampCol rec;
			ICFBamTimestampColObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimestampColObj realised = (ICFBamTimestampColObj)obj.realise();
			}
		}
		int len = allTimestampCol.size();
		ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
		Iterator<ICFBamTimestampColObj> valIter = allTimestampCol.values().iterator();
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
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
		List<ICFBamTimestampColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimestampColObj> readCachedAllTimestampCol() {
		final String S_ProcName = "readCachedAllTimestampCol";
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>();
		if( allTimestampCol != null ) {
			int len = allTimestampCol.size();
			ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
			Iterator<ICFBamTimestampColObj> valIter = allTimestampCol.values().iterator();
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
		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
	public ICFBamTimestampColObj readTimestampColByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readTimestampColByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamTimestampColObj readTimestampColByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamTimestampColObj obj = readTimestampCol( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamTimestampColObj readTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readTimestampColByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamTimestampColObj readTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamTimestampColObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamTimestampColObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamTimestampColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readTimestampColByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimestampColByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimestampColObj realised = (ICFBamTimestampColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
		Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
		List<ICFBamTimestampColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readTimestampColByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimestampColByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimestampColObj realised = (ICFBamTimestampColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
		Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
		List<ICFBamTimestampColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readTimestampColByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimestampColByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimestampColObj realised = (ICFBamTimestampColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
		Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
		List<ICFBamTimestampColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readTimestampColByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimestampColByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByNextIdx( null,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimestampColObj realised = (ICFBamTimestampColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
		Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
		List<ICFBamTimestampColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readTimestampColByContPrevIdx( ScopeId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimestampColByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContPrevIdx( null,
				ScopeId,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimestampColObj realised = (ICFBamTimestampColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
		Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
		List<ICFBamTimestampColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		return( readTimestampColByContNextIdx( ScopeId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimestampColByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContNextIdx( null,
				ScopeId,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimestampColObj realised = (ICFBamTimestampColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
		Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
		List<ICFBamTimestampColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readTimestampColByTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamTimestampColObj> readTimestampColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readTimestampColByTableIdx";
		ICFBamTimestampColByTableIdxKey key = schema.getCFBamBackingStore().getFactoryTimestampCol().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
		if( indexByTableIdx == null ) {
			indexByTableIdx = new HashMap< ICFBamTimestampColByTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( ( ! forceRead ) && indexByTableIdx.containsKey( key ) ) {
			dict = indexByTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTimestampColObj>();
			ICFBamTimestampColObj obj;
			ICFBamTimestampCol[] recList = schema.getCFBamBackingStore().getTableTimestampCol().readDerivedByTableIdx( null,
				TableId );
			ICFBamTimestampCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTimestampColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTimestampColObj realised = (ICFBamTimestampColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
		Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
		List<ICFBamTimestampColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamTimestampColObj readCachedTimestampColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTimestampColObj obj = null;
		obj = readCachedTimestampCol( Id );
		return( obj );
	}

	@Override
	public ICFBamTimestampColObj readCachedTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamTimestampColObj obj = null;
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
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
			Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
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
	public List<ICFBamTimestampColObj> readCachedTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedTimestampColByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
				Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
			ICFBamTimestampColObj obj;
			Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
	public List<ICFBamTimestampColObj> readCachedTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedTimestampColByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
				Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
			ICFBamTimestampColObj obj;
			Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
	public List<ICFBamTimestampColObj> readCachedTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedTimestampColByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
				Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
			ICFBamTimestampColObj obj;
			Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
	public List<ICFBamTimestampColObj> readCachedTimestampColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedTimestampColByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
				Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
			ICFBamTimestampColObj obj;
			Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
	public List<ICFBamTimestampColObj> readCachedTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedTimestampColByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>();
		if( indexByContPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
				Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
			ICFBamTimestampColObj obj;
			Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
	public List<ICFBamTimestampColObj> readCachedTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedTimestampColByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>();
		if( indexByContNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
				Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
			ICFBamTimestampColObj obj;
			Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
	public List<ICFBamTimestampColObj> readCachedTimestampColByTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedTimestampColByTableIdx";
		ICFBamTimestampColByTableIdxKey key = schema.getCFBamBackingStore().getFactoryTimestampCol().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamTimestampColObj> arrayList = new ArrayList<ICFBamTimestampColObj>();
		if( indexByTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict;
			if( indexByTableIdx.containsKey( key ) ) {
				dict = indexByTableIdx.get( key );
				int len = dict.size();
				ICFBamTimestampColObj arr[] = new ICFBamTimestampColObj[len];
				Iterator<ICFBamTimestampColObj> valIter = dict.values().iterator();
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
			ICFBamTimestampColObj obj;
			Iterator<ICFBamTimestampColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTimestampColObj> cmp = new Comparator<ICFBamTimestampColObj>() {
			@Override
			public int compare( ICFBamTimestampColObj lhs, ICFBamTimestampColObj rhs ) {
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
	public void deepDisposeTimestampColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTimestampColObj obj = readCachedTimestampColByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamTimestampColObj obj = readCachedTimestampColByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeTimestampColByScopeIdx";
		ICFBamTimestampColObj obj;
		List<ICFBamTimestampColObj> arrayList = readCachedTimestampColByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamTimestampColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeTimestampColByDefSchemaIdx";
		ICFBamTimestampColObj obj;
		List<ICFBamTimestampColObj> arrayList = readCachedTimestampColByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamTimestampColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeTimestampColByPrevIdx";
		ICFBamTimestampColObj obj;
		List<ICFBamTimestampColObj> arrayList = readCachedTimestampColByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamTimestampColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimestampColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeTimestampColByNextIdx";
		ICFBamTimestampColObj obj;
		List<ICFBamTimestampColObj> arrayList = readCachedTimestampColByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamTimestampColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeTimestampColByContPrevIdx";
		ICFBamTimestampColObj obj;
		List<ICFBamTimestampColObj> arrayList = readCachedTimestampColByContPrevIdx( ScopeId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamTimestampColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeTimestampColByContNextIdx";
		ICFBamTimestampColObj obj;
		List<ICFBamTimestampColObj> arrayList = readCachedTimestampColByContNextIdx( ScopeId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamTimestampColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTimestampColByTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeTimestampColByTableIdx";
		ICFBamTimestampColObj obj;
		List<ICFBamTimestampColObj> arrayList = readCachedTimestampColByTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamTimestampColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamTimestampColObj updateTimestampCol( ICFBamTimestampColObj Obj ) {
		ICFBamTimestampColObj obj = Obj;
		schema.getCFBamBackingStore().getTableTimestampCol().updateTimestampCol( null,
			Obj.getTimestampColRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getTimestampColTableObj().getClassCode() ) {
			obj = (ICFBamTimestampColObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteTimestampCol( ICFBamTimestampColObj Obj ) {
		ICFBamTimestampColObj obj = Obj;
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		ICFBamValueObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampCol( null,
			obj.getTimestampColRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteTimestampColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTimestampColObj obj = readTimestampCol(Id);
		if( obj != null ) {
			ICFBamTimestampColEditObj editObj = (ICFBamTimestampColEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamTimestampColEditObj)obj.beginEdit();
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
		deepDisposeTimestampColByIdIdx( Id );
	}

	@Override
	public void deleteTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamTimestampColObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamTimestampColObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeTimestampColByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamTimestampColObj> iter = dict.values().iterator();
			ICFBamTimestampColObj obj;
			List<ICFBamTimestampColObj> toForget = new LinkedList<ICFBamTimestampColObj>();
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
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByScopeIdx( null,
				ScopeId );
		}
		deepDisposeTimestampColByScopeIdx( ScopeId );
	}

	@Override
	public void deleteTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamTimestampColObj> iter = dict.values().iterator();
			ICFBamTimestampColObj obj;
			List<ICFBamTimestampColObj> toForget = new LinkedList<ICFBamTimestampColObj>();
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
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeTimestampColByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByPrevIdx( null,
				PrevId );
			Iterator<ICFBamTimestampColObj> iter = dict.values().iterator();
			ICFBamTimestampColObj obj;
			List<ICFBamTimestampColObj> toForget = new LinkedList<ICFBamTimestampColObj>();
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
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByPrevIdx( null,
				PrevId );
		}
		deepDisposeTimestampColByPrevIdx( PrevId );
	}

	@Override
	public void deleteTimestampColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByNextIdx( null,
				NextId );
			Iterator<ICFBamTimestampColObj> iter = dict.values().iterator();
			ICFBamTimestampColObj obj;
			List<ICFBamTimestampColObj> toForget = new LinkedList<ICFBamTimestampColObj>();
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
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByNextIdx( null,
				NextId );
		}
		deepDisposeTimestampColByNextIdx( NextId );
	}

	@Override
	public void deleteTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByContPrevIdx( null,
				ScopeId,
				PrevId );
			Iterator<ICFBamTimestampColObj> iter = dict.values().iterator();
			ICFBamTimestampColObj obj;
			List<ICFBamTimestampColObj> toForget = new LinkedList<ICFBamTimestampColObj>();
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
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByContPrevIdx( null,
				ScopeId,
				PrevId );
		}
		deepDisposeTimestampColByContPrevIdx( ScopeId,
				PrevId );
	}

	@Override
	public void deleteTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByContNextIdx( null,
				ScopeId,
				NextId );
			Iterator<ICFBamTimestampColObj> iter = dict.values().iterator();
			ICFBamTimestampColObj obj;
			List<ICFBamTimestampColObj> toForget = new LinkedList<ICFBamTimestampColObj>();
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
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByContNextIdx( null,
				ScopeId,
				NextId );
		}
		deepDisposeTimestampColByContNextIdx( ScopeId,
				NextId );
	}

	@Override
	public void deleteTimestampColByTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamTimestampColByTableIdxKey key = schema.getCFBamBackingStore().getFactoryTimestampCol().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByTableIdx == null ) {
			indexByTableIdx = new HashMap< ICFBamTimestampColByTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTimestampColObj > >();
		}
		if( indexByTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTimestampColObj> dict = indexByTableIdx.get( key );
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByTableIdx( null,
				TableId );
			Iterator<ICFBamTimestampColObj> iter = dict.values().iterator();
			ICFBamTimestampColObj obj;
			List<ICFBamTimestampColObj> toForget = new LinkedList<ICFBamTimestampColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTimestampCol().deleteTimestampColByTableIdx( null,
				TableId );
		}
		deepDisposeTimestampColByTableIdx( TableId );
	}

	/**
	 *	Move the CFBamTimestampColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimestampColObj refreshed cache instance.
	 */
	@Override
	public ICFBamTimestampColObj moveUpTimestampCol( ICFBamTimestampColObj Obj ) {
		ICFBamTimestampColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpTimestampCol" );
		}
		ICFBamTimestampCol rec = schema.getCFBamBackingStore().getTableTimestampCol().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getTimestampColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamTimestampColObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamValueObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamTimestampColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimestampColObj refreshed cache instance.
	 */
	@Override
	public ICFBamTimestampColObj moveDownTimestampCol( ICFBamTimestampColObj Obj ) {
		ICFBamTimestampColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownTimestampCol" );
		}
		ICFBamTimestampCol rec = schema.getCFBamBackingStore().getTableTimestampCol().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getTimestampColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamTimestampColObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamValueObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}