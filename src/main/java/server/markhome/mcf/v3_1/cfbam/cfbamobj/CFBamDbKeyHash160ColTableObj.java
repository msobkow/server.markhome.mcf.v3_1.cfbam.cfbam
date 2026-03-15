// Description: Java 25 Table Object implementation for DbKeyHash160Col.

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

public class CFBamDbKeyHash160ColTableObj
	implements ICFBamDbKeyHash160ColTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamDbKeyHash160Col.CLASS_CODE;
	protected static final int backingClassCode = ICFBamDbKeyHash160Col.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> members;
	private Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> allDbKeyHash160Col;
	private Map< ICFBamValueByUNameIdxKey,
		ICFBamDbKeyHash160ColObj > indexByUNameIdx;
	private Map< ICFBamValueByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > > indexByScopeIdx;
	private Map< ICFBamValueByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > > indexByDefSchemaIdx;
	private Map< ICFBamValueByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > > indexByPrevIdx;
	private Map< ICFBamValueByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > > indexByNextIdx;
	private Map< ICFBamValueByContPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > > indexByContPrevIdx;
	private Map< ICFBamValueByContNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > > indexByContNextIdx;
	private Map< ICFBamDbKeyHash160ColByTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > > indexByTableIdx;
	public static String TABLE_NAME = "DbKeyHash160Col";
	public static String TABLE_DBNAME = "dbk160col";

	public CFBamDbKeyHash160ColTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
		allDbKeyHash160Col = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexByTableIdx = null;
	}

	public CFBamDbKeyHash160ColTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
		allDbKeyHash160Col = null;
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
		return CFBamDbKeyHash160ColTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamDbKeyHash160ColTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allDbKeyHash160Col = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexByTableIdx = null;
		List<ICFBamDbKeyHash160ColObj> toForget = new LinkedList<ICFBamDbKeyHash160ColObj>();
		ICFBamDbKeyHash160ColObj cur = null;
		Iterator<ICFBamDbKeyHash160ColObj> iter = members.values().iterator();
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
	 *	CFBamDbKeyHash160ColObj.
	 */
	@Override
	public ICFBamDbKeyHash160ColObj newInstance() {
		ICFBamDbKeyHash160ColObj inst = new CFBamDbKeyHash160ColObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDbKeyHash160ColObj.
	 */
	@Override
	public ICFBamDbKeyHash160ColEditObj newEditInstance( ICFBamDbKeyHash160ColObj orig ) {
		ICFBamDbKeyHash160ColEditObj edit = new CFBamDbKeyHash160ColEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamDbKeyHash160ColObj realiseDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj ) {
		ICFBamDbKeyHash160ColObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDbKeyHash160ColObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamDbKeyHash160ColObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamDbKeyHash160ColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash160Col().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
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
			keepObj = (ICFBamDbKeyHash160ColObj)schema.getDbKeyHash160DefTableObj().realiseDbKeyHash160Def( Obj );

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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamDbKeyHash160ColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash160Col().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allDbKeyHash160Col != null ) {
				allDbKeyHash160Col.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamDbKeyHash160ColObj)schema.getDbKeyHash160DefTableObj().realiseDbKeyHash160Def( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allDbKeyHash160Col != null ) {
				allDbKeyHash160Col.put( keepObj.getPKey(), keepObj );
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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamDbKeyHash160ColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash160Col().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamDbKeyHash160ColObj createDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj ) {
		ICFBamDbKeyHash160ColObj obj = Obj;
		ICFBamDbKeyHash160Col rec = obj.getDbKeyHash160ColRec();
		schema.getCFBamBackingStore().getTableDbKeyHash160Col().createDbKeyHash160Col(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamDbKeyHash160ColObj)(obj.realise());
		}
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash160ColObj readDbKeyHash160Col( CFLibDbKeyHash256 pkey ) {
		return( readDbKeyHash160Col( pkey, false ) );
	}

	@Override
	public ICFBamDbKeyHash160ColObj readDbKeyHash160Col( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamDbKeyHash160ColObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamDbKeyHash160Col readRec = schema.getCFBamBackingStore().getTableDbKeyHash160Col().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamDbKeyHash160ColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash160ColObj readCachedDbKeyHash160Col( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash160ColObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeDbKeyHash160Col( ICFBamDbKeyHash160ColObj obj )
	{
		final String S_ProcName = "CFBamDbKeyHash160ColTableObj.reallyDeepDisposeDbKeyHash160Col() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDbKeyHash160ColObj existing = readCachedDbKeyHash160Col( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamDbKeyHash160ColByTableIdxKey keyTableIdx = schema.getCFBamBackingStore().getFactoryDbKeyHash160Col().newByTableIdxKey();
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


		schema.getDbKeyHash160DefTableObj().reallyDeepDisposeDbKeyHash160Def( obj );
	}
	@Override
	public void deepDisposeDbKeyHash160Col( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash160ColObj obj = readCachedDbKeyHash160Col( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDbKeyHash160ColObj lockDbKeyHash160Col( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash160ColObj locked = null;
		ICFBamDbKeyHash160Col lockRec = schema.getCFBamBackingStore().getTableDbKeyHash160Col().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamDbKeyHash160ColObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockDbKeyHash160Col", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readAllDbKeyHash160Col() {
		return( readAllDbKeyHash160Col( false ) );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readAllDbKeyHash160Col( boolean forceRead ) {
		final String S_ProcName = "readAllDbKeyHash160Col";
		if( ( allDbKeyHash160Col == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> map = new HashMap<CFLibDbKeyHash256,ICFBamDbKeyHash160ColObj>();
			allDbKeyHash160Col = map;
			ICFBamDbKeyHash160Col[] recList = schema.getCFBamBackingStore().getTableDbKeyHash160Col().readAllDerived( null );
			ICFBamDbKeyHash160Col rec;
			ICFBamDbKeyHash160ColObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash160ColObj realised = (ICFBamDbKeyHash160ColObj)obj.realise();
			}
		}
		int len = allDbKeyHash160Col.size();
		ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
		Iterator<ICFBamDbKeyHash160ColObj> valIter = allDbKeyHash160Col.values().iterator();
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
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
		List<ICFBamDbKeyHash160ColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readCachedAllDbKeyHash160Col() {
		final String S_ProcName = "readCachedAllDbKeyHash160Col";
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>();
		if( allDbKeyHash160Col != null ) {
			int len = allDbKeyHash160Col.size();
			ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
			Iterator<ICFBamDbKeyHash160ColObj> valIter = allDbKeyHash160Col.values().iterator();
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
		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
	public ICFBamDbKeyHash160ColObj readDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readDbKeyHash160ColByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamDbKeyHash160ColObj readDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamDbKeyHash160ColObj obj = readDbKeyHash160Col( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash160ColObj readDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readDbKeyHash160ColByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamDbKeyHash160ColObj readDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamDbKeyHash160ColObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamDbKeyHash160ColObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamDbKeyHash160ColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readDbKeyHash160ColByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash160ColByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash160ColObj realised = (ICFBamDbKeyHash160ColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
		Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
		List<ICFBamDbKeyHash160ColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readDbKeyHash160ColByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash160ColByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash160ColObj realised = (ICFBamDbKeyHash160ColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
		Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
		List<ICFBamDbKeyHash160ColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readDbKeyHash160ColByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash160ColByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash160ColObj realised = (ICFBamDbKeyHash160ColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
		Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
		List<ICFBamDbKeyHash160ColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readDbKeyHash160ColByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash160ColByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByNextIdx( null,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash160ColObj realised = (ICFBamDbKeyHash160ColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
		Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
		List<ICFBamDbKeyHash160ColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readDbKeyHash160ColByContPrevIdx( ScopeId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash160ColByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContPrevIdx( null,
				ScopeId,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash160ColObj realised = (ICFBamDbKeyHash160ColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
		Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
		List<ICFBamDbKeyHash160ColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		return( readDbKeyHash160ColByContNextIdx( ScopeId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash160ColByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContNextIdx( null,
				ScopeId,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash160ColObj realised = (ICFBamDbKeyHash160ColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
		Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
		List<ICFBamDbKeyHash160ColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readDbKeyHash160ColByTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash160ColByTableIdx";
		ICFBamDbKeyHash160ColByTableIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash160Col().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
		if( indexByTableIdx == null ) {
			indexByTableIdx = new HashMap< ICFBamDbKeyHash160ColByTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( ( ! forceRead ) && indexByTableIdx.containsKey( key ) ) {
			dict = indexByTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj>();
			ICFBamDbKeyHash160ColObj obj;
			ICFBamDbKeyHash160Col[] recList = schema.getCFBamBackingStore().getTableDbKeyHash160Col().readDerivedByTableIdx( null,
				TableId );
			ICFBamDbKeyHash160Col rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash160ColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash160ColObj realised = (ICFBamDbKeyHash160ColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
		Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
		List<ICFBamDbKeyHash160ColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDbKeyHash160ColObj readCachedDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash160ColObj obj = null;
		obj = readCachedDbKeyHash160Col( Id );
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash160ColObj readCachedDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamDbKeyHash160ColObj obj = null;
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
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
			Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
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
	public List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedDbKeyHash160ColByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
				Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash160ColObj obj;
			Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
	public List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedDbKeyHash160ColByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
				Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash160ColObj obj;
			Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
	public List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDbKeyHash160ColByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
				Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash160ColObj obj;
			Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
	public List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDbKeyHash160ColByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
				Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash160ColObj obj;
			Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
	public List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDbKeyHash160ColByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>();
		if( indexByContPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
				Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash160ColObj obj;
			Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
	public List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDbKeyHash160ColByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>();
		if( indexByContNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
				Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash160ColObj obj;
			Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
	public List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedDbKeyHash160ColByTableIdx";
		ICFBamDbKeyHash160ColByTableIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash160Col().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamDbKeyHash160ColObj> arrayList = new ArrayList<ICFBamDbKeyHash160ColObj>();
		if( indexByTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict;
			if( indexByTableIdx.containsKey( key ) ) {
				dict = indexByTableIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash160ColObj arr[] = new ICFBamDbKeyHash160ColObj[len];
				Iterator<ICFBamDbKeyHash160ColObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash160ColObj obj;
			Iterator<ICFBamDbKeyHash160ColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash160ColObj> cmp = new Comparator<ICFBamDbKeyHash160ColObj>() {
			@Override
			public int compare( ICFBamDbKeyHash160ColObj lhs, ICFBamDbKeyHash160ColObj rhs ) {
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
	public void deepDisposeDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash160ColObj obj = readCachedDbKeyHash160ColByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamDbKeyHash160ColObj obj = readCachedDbKeyHash160ColByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash160ColByScopeIdx";
		ICFBamDbKeyHash160ColObj obj;
		List<ICFBamDbKeyHash160ColObj> arrayList = readCachedDbKeyHash160ColByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash160ColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash160ColByDefSchemaIdx";
		ICFBamDbKeyHash160ColObj obj;
		List<ICFBamDbKeyHash160ColObj> arrayList = readCachedDbKeyHash160ColByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash160ColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash160ColByPrevIdx";
		ICFBamDbKeyHash160ColObj obj;
		List<ICFBamDbKeyHash160ColObj> arrayList = readCachedDbKeyHash160ColByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash160ColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash160ColByNextIdx";
		ICFBamDbKeyHash160ColObj obj;
		List<ICFBamDbKeyHash160ColObj> arrayList = readCachedDbKeyHash160ColByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash160ColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash160ColByContPrevIdx";
		ICFBamDbKeyHash160ColObj obj;
		List<ICFBamDbKeyHash160ColObj> arrayList = readCachedDbKeyHash160ColByContPrevIdx( ScopeId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash160ColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash160ColByContNextIdx";
		ICFBamDbKeyHash160ColObj obj;
		List<ICFBamDbKeyHash160ColObj> arrayList = readCachedDbKeyHash160ColByContNextIdx( ScopeId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash160ColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash160ColByTableIdx";
		ICFBamDbKeyHash160ColObj obj;
		List<ICFBamDbKeyHash160ColObj> arrayList = readCachedDbKeyHash160ColByTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash160ColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamDbKeyHash160ColObj updateDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj ) {
		ICFBamDbKeyHash160ColObj obj = Obj;
		schema.getCFBamBackingStore().getTableDbKeyHash160Col().updateDbKeyHash160Col( null,
			Obj.getDbKeyHash160ColRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getDbKeyHash160ColTableObj().getClassCode() ) {
			obj = (ICFBamDbKeyHash160ColObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj ) {
		ICFBamDbKeyHash160ColObj obj = Obj;
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		ICFBamValueObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160Col( null,
			obj.getDbKeyHash160ColRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash160ColObj obj = readDbKeyHash160Col(Id);
		if( obj != null ) {
			ICFBamDbKeyHash160ColEditObj editObj = (ICFBamDbKeyHash160ColEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamDbKeyHash160ColEditObj)obj.beginEdit();
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
		deepDisposeDbKeyHash160ColByIdIdx( Id );
	}

	@Override
	public void deleteDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamDbKeyHash160ColObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamDbKeyHash160ColObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeDbKeyHash160ColByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamDbKeyHash160ColObj> iter = dict.values().iterator();
			ICFBamDbKeyHash160ColObj obj;
			List<ICFBamDbKeyHash160ColObj> toForget = new LinkedList<ICFBamDbKeyHash160ColObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByScopeIdx( null,
				ScopeId );
		}
		deepDisposeDbKeyHash160ColByScopeIdx( ScopeId );
	}

	@Override
	public void deleteDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamDbKeyHash160ColObj> iter = dict.values().iterator();
			ICFBamDbKeyHash160ColObj obj;
			List<ICFBamDbKeyHash160ColObj> toForget = new LinkedList<ICFBamDbKeyHash160ColObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeDbKeyHash160ColByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByPrevIdx( null,
				PrevId );
			Iterator<ICFBamDbKeyHash160ColObj> iter = dict.values().iterator();
			ICFBamDbKeyHash160ColObj obj;
			List<ICFBamDbKeyHash160ColObj> toForget = new LinkedList<ICFBamDbKeyHash160ColObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByPrevIdx( null,
				PrevId );
		}
		deepDisposeDbKeyHash160ColByPrevIdx( PrevId );
	}

	@Override
	public void deleteDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByNextIdx( null,
				NextId );
			Iterator<ICFBamDbKeyHash160ColObj> iter = dict.values().iterator();
			ICFBamDbKeyHash160ColObj obj;
			List<ICFBamDbKeyHash160ColObj> toForget = new LinkedList<ICFBamDbKeyHash160ColObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByNextIdx( null,
				NextId );
		}
		deepDisposeDbKeyHash160ColByNextIdx( NextId );
	}

	@Override
	public void deleteDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByContPrevIdx( null,
				ScopeId,
				PrevId );
			Iterator<ICFBamDbKeyHash160ColObj> iter = dict.values().iterator();
			ICFBamDbKeyHash160ColObj obj;
			List<ICFBamDbKeyHash160ColObj> toForget = new LinkedList<ICFBamDbKeyHash160ColObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByContPrevIdx( null,
				ScopeId,
				PrevId );
		}
		deepDisposeDbKeyHash160ColByContPrevIdx( ScopeId,
				PrevId );
	}

	@Override
	public void deleteDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByContNextIdx( null,
				ScopeId,
				NextId );
			Iterator<ICFBamDbKeyHash160ColObj> iter = dict.values().iterator();
			ICFBamDbKeyHash160ColObj obj;
			List<ICFBamDbKeyHash160ColObj> toForget = new LinkedList<ICFBamDbKeyHash160ColObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByContNextIdx( null,
				ScopeId,
				NextId );
		}
		deepDisposeDbKeyHash160ColByContNextIdx( ScopeId,
				NextId );
	}

	@Override
	public void deleteDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamDbKeyHash160ColByTableIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash160Col().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByTableIdx == null ) {
			indexByTableIdx = new HashMap< ICFBamDbKeyHash160ColByTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj > >();
		}
		if( indexByTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash160ColObj> dict = indexByTableIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByTableIdx( null,
				TableId );
			Iterator<ICFBamDbKeyHash160ColObj> iter = dict.values().iterator();
			ICFBamDbKeyHash160ColObj obj;
			List<ICFBamDbKeyHash160ColObj> toForget = new LinkedList<ICFBamDbKeyHash160ColObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash160Col().deleteDbKeyHash160ColByTableIdx( null,
				TableId );
		}
		deepDisposeDbKeyHash160ColByTableIdx( TableId );
	}

	/**
	 *	Move the CFBamDbKeyHash160ColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash160ColObj refreshed cache instance.
	 */
	@Override
	public ICFBamDbKeyHash160ColObj moveUpDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj ) {
		ICFBamDbKeyHash160ColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpDbKeyHash160Col" );
		}
		ICFBamDbKeyHash160Col rec = schema.getCFBamBackingStore().getTableDbKeyHash160Col().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDbKeyHash160ColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDbKeyHash160ColObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamValueObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamDbKeyHash160ColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash160ColObj refreshed cache instance.
	 */
	@Override
	public ICFBamDbKeyHash160ColObj moveDownDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj ) {
		ICFBamDbKeyHash160ColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownDbKeyHash160Col" );
		}
		ICFBamDbKeyHash160Col rec = schema.getCFBamBackingStore().getTableDbKeyHash160Col().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDbKeyHash160ColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDbKeyHash160ColObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamValueObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}