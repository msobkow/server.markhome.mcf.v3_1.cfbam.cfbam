// Description: Java 25 Table Object implementation for DbKeyHash512Type.

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

public class CFBamDbKeyHash512TypeTableObj
	implements ICFBamDbKeyHash512TypeTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamDbKeyHash512Type.CLASS_CODE;
	protected static final int backingClassCode = ICFBamDbKeyHash512Type.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> members;
	private Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> allDbKeyHash512Type;
	private Map< ICFBamValueByUNameIdxKey,
		ICFBamDbKeyHash512TypeObj > indexByUNameIdx;
	private Map< ICFBamValueByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > > indexByScopeIdx;
	private Map< ICFBamValueByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > > indexByDefSchemaIdx;
	private Map< ICFBamValueByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > > indexByPrevIdx;
	private Map< ICFBamValueByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > > indexByNextIdx;
	private Map< ICFBamValueByContPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > > indexByContPrevIdx;
	private Map< ICFBamValueByContNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > > indexByContNextIdx;
	private Map< ICFBamDbKeyHash512TypeBySchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > > indexBySchemaIdx;
	public static String TABLE_NAME = "DbKeyHash512Type";
	public static String TABLE_DBNAME = "dbk512typ";

	public CFBamDbKeyHash512TypeTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
		allDbKeyHash512Type = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
	}

	public CFBamDbKeyHash512TypeTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
		allDbKeyHash512Type = null;
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
		return CFBamDbKeyHash512TypeTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamDbKeyHash512TypeTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allDbKeyHash512Type = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
		List<ICFBamDbKeyHash512TypeObj> toForget = new LinkedList<ICFBamDbKeyHash512TypeObj>();
		ICFBamDbKeyHash512TypeObj cur = null;
		Iterator<ICFBamDbKeyHash512TypeObj> iter = members.values().iterator();
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
	 *	CFBamDbKeyHash512TypeObj.
	 */
	@Override
	public ICFBamDbKeyHash512TypeObj newInstance() {
		ICFBamDbKeyHash512TypeObj inst = new CFBamDbKeyHash512TypeObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDbKeyHash512TypeObj.
	 */
	@Override
	public ICFBamDbKeyHash512TypeEditObj newEditInstance( ICFBamDbKeyHash512TypeObj orig ) {
		ICFBamDbKeyHash512TypeEditObj edit = new CFBamDbKeyHash512TypeEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj realiseDbKeyHash512Type( ICFBamDbKeyHash512TypeObj Obj ) {
		ICFBamDbKeyHash512TypeObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDbKeyHash512TypeObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamDbKeyHash512TypeObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamDbKeyHash512TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash512Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
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
			keepObj = (ICFBamDbKeyHash512TypeObj)schema.getDbKeyHash512DefTableObj().realiseDbKeyHash512Def( Obj );

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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamDbKeyHash512TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash512Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allDbKeyHash512Type != null ) {
				allDbKeyHash512Type.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamDbKeyHash512TypeObj)schema.getDbKeyHash512DefTableObj().realiseDbKeyHash512Def( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allDbKeyHash512Type != null ) {
				allDbKeyHash512Type.put( keepObj.getPKey(), keepObj );
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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamDbKeyHash512TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash512Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj createDbKeyHash512Type( ICFBamDbKeyHash512TypeObj Obj ) {
		ICFBamDbKeyHash512TypeObj obj = Obj;
		ICFBamDbKeyHash512Type rec = obj.getDbKeyHash512TypeRec();
		schema.getCFBamBackingStore().getTableDbKeyHash512Type().createDbKeyHash512Type(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamDbKeyHash512TypeObj)(obj.realise());
		}
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj readDbKeyHash512Type( CFLibDbKeyHash256 pkey ) {
		return( readDbKeyHash512Type( pkey, false ) );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj readDbKeyHash512Type( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamDbKeyHash512TypeObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamDbKeyHash512Type readRec = schema.getCFBamBackingStore().getTableDbKeyHash512Type().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamDbKeyHash512TypeObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj readCachedDbKeyHash512Type( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash512TypeObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeDbKeyHash512Type( ICFBamDbKeyHash512TypeObj obj )
	{
		final String S_ProcName = "CFBamDbKeyHash512TypeTableObj.reallyDeepDisposeDbKeyHash512Type() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDbKeyHash512TypeObj existing = readCachedDbKeyHash512Type( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamDbKeyHash512TypeBySchemaIdxKey keySchemaIdx = schema.getCFBamBackingStore().getFactoryDbKeyHash512Type().newBySchemaIdxKey();
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


		schema.getDbKeyHash512DefTableObj().reallyDeepDisposeDbKeyHash512Def( obj );
	}
	@Override
	public void deepDisposeDbKeyHash512Type( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash512TypeObj obj = readCachedDbKeyHash512Type( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDbKeyHash512TypeObj lockDbKeyHash512Type( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash512TypeObj locked = null;
		ICFBamDbKeyHash512Type lockRec = schema.getCFBamBackingStore().getTableDbKeyHash512Type().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamDbKeyHash512TypeObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockDbKeyHash512Type", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readAllDbKeyHash512Type() {
		return( readAllDbKeyHash512Type( false ) );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readAllDbKeyHash512Type( boolean forceRead ) {
		final String S_ProcName = "readAllDbKeyHash512Type";
		if( ( allDbKeyHash512Type == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> map = new HashMap<CFLibDbKeyHash256,ICFBamDbKeyHash512TypeObj>();
			allDbKeyHash512Type = map;
			ICFBamDbKeyHash512Type[] recList = schema.getCFBamBackingStore().getTableDbKeyHash512Type().readAllDerived( null );
			ICFBamDbKeyHash512Type rec;
			ICFBamDbKeyHash512TypeObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash512TypeObj realised = (ICFBamDbKeyHash512TypeObj)obj.realise();
			}
		}
		int len = allDbKeyHash512Type.size();
		ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
		Iterator<ICFBamDbKeyHash512TypeObj> valIter = allDbKeyHash512Type.values().iterator();
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
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
		List<ICFBamDbKeyHash512TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readCachedAllDbKeyHash512Type() {
		final String S_ProcName = "readCachedAllDbKeyHash512Type";
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>();
		if( allDbKeyHash512Type != null ) {
			int len = allDbKeyHash512Type.size();
			ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = allDbKeyHash512Type.values().iterator();
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
		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
	public ICFBamDbKeyHash512TypeObj readDbKeyHash512TypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readDbKeyHash512TypeByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj readDbKeyHash512TypeByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamDbKeyHash512TypeObj obj = readDbKeyHash512Type( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj readDbKeyHash512TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readDbKeyHash512TypeByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj readDbKeyHash512TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamDbKeyHash512TypeObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamDbKeyHash512TypeObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamDbKeyHash512TypeObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readDbKeyHash512TypeByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash512TypeByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash512TypeObj realised = (ICFBamDbKeyHash512TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
		Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
		List<ICFBamDbKeyHash512TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readDbKeyHash512TypeByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash512TypeByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash512TypeObj realised = (ICFBamDbKeyHash512TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
		Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
		List<ICFBamDbKeyHash512TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readDbKeyHash512TypeByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash512TypeByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash512TypeObj realised = (ICFBamDbKeyHash512TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
		Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
		List<ICFBamDbKeyHash512TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readDbKeyHash512TypeByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash512TypeByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByNextIdx( null,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash512TypeObj realised = (ICFBamDbKeyHash512TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
		Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
		List<ICFBamDbKeyHash512TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readDbKeyHash512TypeByContPrevIdx( ScopeId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash512TypeByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContPrevIdx( null,
				ScopeId,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash512TypeObj realised = (ICFBamDbKeyHash512TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
		Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
		List<ICFBamDbKeyHash512TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		return( readDbKeyHash512TypeByContNextIdx( ScopeId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash512TypeByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContNextIdx( null,
				ScopeId,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash512TypeObj realised = (ICFBamDbKeyHash512TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
		Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
		List<ICFBamDbKeyHash512TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		return( readDbKeyHash512TypeBySchemaIdx( SchemaDefId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash512TypeObj> readDbKeyHash512TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash512TypeBySchemaIdx";
		ICFBamDbKeyHash512TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash512Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamDbKeyHash512TypeBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaIdx.containsKey( key ) ) {
			dict = indexBySchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj>();
			ICFBamDbKeyHash512TypeObj obj;
			ICFBamDbKeyHash512Type[] recList = schema.getCFBamBackingStore().getTableDbKeyHash512Type().readDerivedBySchemaIdx( null,
				SchemaDefId );
			ICFBamDbKeyHash512Type rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash512TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash512TypeObj realised = (ICFBamDbKeyHash512TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
		Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
		List<ICFBamDbKeyHash512TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj readCachedDbKeyHash512TypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash512TypeObj obj = null;
		obj = readCachedDbKeyHash512Type( Id );
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash512TypeObj readCachedDbKeyHash512TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamDbKeyHash512TypeObj obj = null;
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
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
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
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
	public List<ICFBamDbKeyHash512TypeObj> readCachedDbKeyHash512TypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedDbKeyHash512TypeByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
				Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash512TypeObj obj;
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
	public List<ICFBamDbKeyHash512TypeObj> readCachedDbKeyHash512TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedDbKeyHash512TypeByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
				Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash512TypeObj obj;
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
	public List<ICFBamDbKeyHash512TypeObj> readCachedDbKeyHash512TypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDbKeyHash512TypeByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
				Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash512TypeObj obj;
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
	public List<ICFBamDbKeyHash512TypeObj> readCachedDbKeyHash512TypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDbKeyHash512TypeByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
				Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash512TypeObj obj;
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
	public List<ICFBamDbKeyHash512TypeObj> readCachedDbKeyHash512TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDbKeyHash512TypeByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>();
		if( indexByContPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
				Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash512TypeObj obj;
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
	public List<ICFBamDbKeyHash512TypeObj> readCachedDbKeyHash512TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDbKeyHash512TypeByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>();
		if( indexByContNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
				Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash512TypeObj obj;
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
	public List<ICFBamDbKeyHash512TypeObj> readCachedDbKeyHash512TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "readCachedDbKeyHash512TypeBySchemaIdx";
		ICFBamDbKeyHash512TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash512Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		ArrayList<ICFBamDbKeyHash512TypeObj> arrayList = new ArrayList<ICFBamDbKeyHash512TypeObj>();
		if( indexBySchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict;
			if( indexBySchemaIdx.containsKey( key ) ) {
				dict = indexBySchemaIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash512TypeObj arr[] = new ICFBamDbKeyHash512TypeObj[len];
				Iterator<ICFBamDbKeyHash512TypeObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash512TypeObj obj;
			Iterator<ICFBamDbKeyHash512TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash512TypeObj> cmp = new Comparator<ICFBamDbKeyHash512TypeObj>() {
			@Override
			public int compare( ICFBamDbKeyHash512TypeObj lhs, ICFBamDbKeyHash512TypeObj rhs ) {
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
	public void deepDisposeDbKeyHash512TypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash512TypeObj obj = readCachedDbKeyHash512TypeByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDbKeyHash512TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamDbKeyHash512TypeObj obj = readCachedDbKeyHash512TypeByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDbKeyHash512TypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash512TypeByScopeIdx";
		ICFBamDbKeyHash512TypeObj obj;
		List<ICFBamDbKeyHash512TypeObj> arrayList = readCachedDbKeyHash512TypeByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash512TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash512TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash512TypeByDefSchemaIdx";
		ICFBamDbKeyHash512TypeObj obj;
		List<ICFBamDbKeyHash512TypeObj> arrayList = readCachedDbKeyHash512TypeByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash512TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash512TypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash512TypeByPrevIdx";
		ICFBamDbKeyHash512TypeObj obj;
		List<ICFBamDbKeyHash512TypeObj> arrayList = readCachedDbKeyHash512TypeByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash512TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash512TypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash512TypeByNextIdx";
		ICFBamDbKeyHash512TypeObj obj;
		List<ICFBamDbKeyHash512TypeObj> arrayList = readCachedDbKeyHash512TypeByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash512TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash512TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash512TypeByContPrevIdx";
		ICFBamDbKeyHash512TypeObj obj;
		List<ICFBamDbKeyHash512TypeObj> arrayList = readCachedDbKeyHash512TypeByContPrevIdx( ScopeId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash512TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash512TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash512TypeByContNextIdx";
		ICFBamDbKeyHash512TypeObj obj;
		List<ICFBamDbKeyHash512TypeObj> arrayList = readCachedDbKeyHash512TypeByContNextIdx( ScopeId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash512TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash512TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash512TypeBySchemaIdx";
		ICFBamDbKeyHash512TypeObj obj;
		List<ICFBamDbKeyHash512TypeObj> arrayList = readCachedDbKeyHash512TypeBySchemaIdx( SchemaDefId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash512TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamDbKeyHash512TypeObj updateDbKeyHash512Type( ICFBamDbKeyHash512TypeObj Obj ) {
		ICFBamDbKeyHash512TypeObj obj = Obj;
		schema.getCFBamBackingStore().getTableDbKeyHash512Type().updateDbKeyHash512Type( null,
			Obj.getDbKeyHash512TypeRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getDbKeyHash512TypeTableObj().getClassCode() ) {
			obj = (ICFBamDbKeyHash512TypeObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteDbKeyHash512Type( ICFBamDbKeyHash512TypeObj Obj ) {
		ICFBamDbKeyHash512TypeObj obj = Obj;
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		ICFBamValueObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512Type( null,
			obj.getDbKeyHash512TypeRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteDbKeyHash512TypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash512TypeObj obj = readDbKeyHash512Type(Id);
		if( obj != null ) {
			ICFBamDbKeyHash512TypeEditObj editObj = (ICFBamDbKeyHash512TypeEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamDbKeyHash512TypeEditObj)obj.beginEdit();
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
		deepDisposeDbKeyHash512TypeByIdIdx( Id );
	}

	@Override
	public void deleteDbKeyHash512TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamDbKeyHash512TypeObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamDbKeyHash512TypeObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeDbKeyHash512TypeByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteDbKeyHash512TypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamDbKeyHash512TypeObj> iter = dict.values().iterator();
			ICFBamDbKeyHash512TypeObj obj;
			List<ICFBamDbKeyHash512TypeObj> toForget = new LinkedList<ICFBamDbKeyHash512TypeObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByScopeIdx( null,
				ScopeId );
		}
		deepDisposeDbKeyHash512TypeByScopeIdx( ScopeId );
	}

	@Override
	public void deleteDbKeyHash512TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamDbKeyHash512TypeObj> iter = dict.values().iterator();
			ICFBamDbKeyHash512TypeObj obj;
			List<ICFBamDbKeyHash512TypeObj> toForget = new LinkedList<ICFBamDbKeyHash512TypeObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeDbKeyHash512TypeByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteDbKeyHash512TypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByPrevIdx( null,
				PrevId );
			Iterator<ICFBamDbKeyHash512TypeObj> iter = dict.values().iterator();
			ICFBamDbKeyHash512TypeObj obj;
			List<ICFBamDbKeyHash512TypeObj> toForget = new LinkedList<ICFBamDbKeyHash512TypeObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByPrevIdx( null,
				PrevId );
		}
		deepDisposeDbKeyHash512TypeByPrevIdx( PrevId );
	}

	@Override
	public void deleteDbKeyHash512TypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByNextIdx( null,
				NextId );
			Iterator<ICFBamDbKeyHash512TypeObj> iter = dict.values().iterator();
			ICFBamDbKeyHash512TypeObj obj;
			List<ICFBamDbKeyHash512TypeObj> toForget = new LinkedList<ICFBamDbKeyHash512TypeObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByNextIdx( null,
				NextId );
		}
		deepDisposeDbKeyHash512TypeByNextIdx( NextId );
	}

	@Override
	public void deleteDbKeyHash512TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByContPrevIdx( null,
				ScopeId,
				PrevId );
			Iterator<ICFBamDbKeyHash512TypeObj> iter = dict.values().iterator();
			ICFBamDbKeyHash512TypeObj obj;
			List<ICFBamDbKeyHash512TypeObj> toForget = new LinkedList<ICFBamDbKeyHash512TypeObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByContPrevIdx( null,
				ScopeId,
				PrevId );
		}
		deepDisposeDbKeyHash512TypeByContPrevIdx( ScopeId,
				PrevId );
	}

	@Override
	public void deleteDbKeyHash512TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByContNextIdx( null,
				ScopeId,
				NextId );
			Iterator<ICFBamDbKeyHash512TypeObj> iter = dict.values().iterator();
			ICFBamDbKeyHash512TypeObj obj;
			List<ICFBamDbKeyHash512TypeObj> toForget = new LinkedList<ICFBamDbKeyHash512TypeObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeByContNextIdx( null,
				ScopeId,
				NextId );
		}
		deepDisposeDbKeyHash512TypeByContNextIdx( ScopeId,
				NextId );
	}

	@Override
	public void deleteDbKeyHash512TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		ICFBamDbKeyHash512TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash512Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamDbKeyHash512TypeBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj > >();
		}
		if( indexBySchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash512TypeObj> dict = indexBySchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeBySchemaIdx( null,
				SchemaDefId );
			Iterator<ICFBamDbKeyHash512TypeObj> iter = dict.values().iterator();
			ICFBamDbKeyHash512TypeObj obj;
			List<ICFBamDbKeyHash512TypeObj> toForget = new LinkedList<ICFBamDbKeyHash512TypeObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash512Type().deleteDbKeyHash512TypeBySchemaIdx( null,
				SchemaDefId );
		}
		deepDisposeDbKeyHash512TypeBySchemaIdx( SchemaDefId );
	}

	/**
	 *	Move the CFBamDbKeyHash512TypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash512TypeObj refreshed cache instance.
	 */
	@Override
	public ICFBamDbKeyHash512TypeObj moveUpDbKeyHash512Type( ICFBamDbKeyHash512TypeObj Obj ) {
		ICFBamDbKeyHash512TypeObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpDbKeyHash512Type" );
		}
		ICFBamDbKeyHash512Type rec = schema.getCFBamBackingStore().getTableDbKeyHash512Type().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDbKeyHash512TypeTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDbKeyHash512TypeObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamValueObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamDbKeyHash512TypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash512TypeObj refreshed cache instance.
	 */
	@Override
	public ICFBamDbKeyHash512TypeObj moveDownDbKeyHash512Type( ICFBamDbKeyHash512TypeObj Obj ) {
		ICFBamDbKeyHash512TypeObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownDbKeyHash512Type" );
		}
		ICFBamDbKeyHash512Type rec = schema.getCFBamBackingStore().getTableDbKeyHash512Type().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDbKeyHash512TypeTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDbKeyHash512TypeObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamValueObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}