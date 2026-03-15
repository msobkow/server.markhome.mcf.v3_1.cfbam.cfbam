// Description: Java 25 Table Object implementation for DbKeyHash128Gen.

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

public class CFBamDbKeyHash128GenTableObj
	implements ICFBamDbKeyHash128GenTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamDbKeyHash128Gen.CLASS_CODE;
	protected static final int backingClassCode = ICFBamDbKeyHash128Gen.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> members;
	private Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> allDbKeyHash128Gen;
	private Map< ICFBamValueByUNameIdxKey,
		ICFBamDbKeyHash128GenObj > indexByUNameIdx;
	private Map< ICFBamValueByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > > indexByScopeIdx;
	private Map< ICFBamValueByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > > indexByDefSchemaIdx;
	private Map< ICFBamValueByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > > indexByPrevIdx;
	private Map< ICFBamValueByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > > indexByNextIdx;
	private Map< ICFBamValueByContPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > > indexByContPrevIdx;
	private Map< ICFBamValueByContNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > > indexByContNextIdx;
	private Map< ICFBamDbKeyHash128TypeBySchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > > indexBySchemaIdx;
	public static String TABLE_NAME = "DbKeyHash128Gen";
	public static String TABLE_DBNAME = "idgdbk128";

	public CFBamDbKeyHash128GenTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
		allDbKeyHash128Gen = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
	}

	public CFBamDbKeyHash128GenTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
		allDbKeyHash128Gen = null;
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
		return CFBamDbKeyHash128GenTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamDbKeyHash128GenTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allDbKeyHash128Gen = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
		List<ICFBamDbKeyHash128GenObj> toForget = new LinkedList<ICFBamDbKeyHash128GenObj>();
		ICFBamDbKeyHash128GenObj cur = null;
		Iterator<ICFBamDbKeyHash128GenObj> iter = members.values().iterator();
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
	 *	CFBamDbKeyHash128GenObj.
	 */
	@Override
	public ICFBamDbKeyHash128GenObj newInstance() {
		ICFBamDbKeyHash128GenObj inst = new CFBamDbKeyHash128GenObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDbKeyHash128GenObj.
	 */
	@Override
	public ICFBamDbKeyHash128GenEditObj newEditInstance( ICFBamDbKeyHash128GenObj orig ) {
		ICFBamDbKeyHash128GenEditObj edit = new CFBamDbKeyHash128GenEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamDbKeyHash128GenObj realiseDbKeyHash128Gen( ICFBamDbKeyHash128GenObj Obj ) {
		ICFBamDbKeyHash128GenObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDbKeyHash128GenObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamDbKeyHash128GenObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamDbKeyHash128TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash128Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					indexBySchemaIdx.remove( keySchemaIdx );
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamDbKeyHash128GenObj)schema.getDbKeyHash128TypeTableObj().realiseDbKeyHash128Type( Obj );

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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamDbKeyHash128TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash128Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allDbKeyHash128Gen != null ) {
				allDbKeyHash128Gen.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamDbKeyHash128GenObj)schema.getDbKeyHash128TypeTableObj().realiseDbKeyHash128Type( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allDbKeyHash128Gen != null ) {
				allDbKeyHash128Gen.put( keepObj.getPKey(), keepObj );
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
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamDbKeyHash128TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryDbKeyHash128Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamDbKeyHash128GenObj createDbKeyHash128Gen( ICFBamDbKeyHash128GenObj Obj ) {
		ICFBamDbKeyHash128GenObj obj = Obj;
		ICFBamDbKeyHash128Gen rec = obj.getDbKeyHash128GenRec();
		schema.getCFBamBackingStore().getTableDbKeyHash128Gen().createDbKeyHash128Gen(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamDbKeyHash128GenObj)(obj.realise());
		}
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash128GenObj readDbKeyHash128Gen( CFLibDbKeyHash256 pkey ) {
		return( readDbKeyHash128Gen( pkey, false ) );
	}

	@Override
	public ICFBamDbKeyHash128GenObj readDbKeyHash128Gen( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamDbKeyHash128GenObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamDbKeyHash128Gen readRec = schema.getCFBamBackingStore().getTableDbKeyHash128Gen().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamDbKeyHash128GenObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash128GenObj readCachedDbKeyHash128Gen( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash128GenObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeDbKeyHash128Gen( ICFBamDbKeyHash128GenObj obj )
	{
		final String S_ProcName = "CFBamDbKeyHash128GenTableObj.reallyDeepDisposeDbKeyHash128Gen() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDbKeyHash128GenObj existing = readCachedDbKeyHash128Gen( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );

		schema.getTableColTableObj().deepDisposeTableColByDataIdx( existing.getRequiredId() );
		schema.getIndexColTableObj().deepDisposeIndexColByColIdx( existing.getRequiredId() );


		schema.getDbKeyHash128TypeTableObj().reallyDeepDisposeDbKeyHash128Type( obj );
	}
	@Override
	public void deepDisposeDbKeyHash128Gen( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash128GenObj obj = readCachedDbKeyHash128Gen( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDbKeyHash128GenObj lockDbKeyHash128Gen( CFLibDbKeyHash256 pkey ) {
		ICFBamDbKeyHash128GenObj locked = null;
		ICFBamDbKeyHash128Gen lockRec = schema.getCFBamBackingStore().getTableDbKeyHash128Gen().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamDbKeyHash128GenObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockDbKeyHash128Gen", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readAllDbKeyHash128Gen() {
		return( readAllDbKeyHash128Gen( false ) );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readAllDbKeyHash128Gen( boolean forceRead ) {
		final String S_ProcName = "readAllDbKeyHash128Gen";
		if( ( allDbKeyHash128Gen == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> map = new HashMap<CFLibDbKeyHash256,ICFBamDbKeyHash128GenObj>();
			allDbKeyHash128Gen = map;
			ICFBamDbKeyHash128Gen[] recList = schema.getCFBamBackingStore().getTableDbKeyHash128Gen().readAllDerived( null );
			ICFBamDbKeyHash128Gen rec;
			ICFBamDbKeyHash128GenObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash128GenObj realised = (ICFBamDbKeyHash128GenObj)obj.realise();
			}
		}
		int len = allDbKeyHash128Gen.size();
		ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
		Iterator<ICFBamDbKeyHash128GenObj> valIter = allDbKeyHash128Gen.values().iterator();
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
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
		List<ICFBamDbKeyHash128GenObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readCachedAllDbKeyHash128Gen() {
		final String S_ProcName = "readCachedAllDbKeyHash128Gen";
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>();
		if( allDbKeyHash128Gen != null ) {
			int len = allDbKeyHash128Gen.size();
			ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
			Iterator<ICFBamDbKeyHash128GenObj> valIter = allDbKeyHash128Gen.values().iterator();
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
		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
	public ICFBamDbKeyHash128GenObj readDbKeyHash128GenByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readDbKeyHash128GenByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamDbKeyHash128GenObj readDbKeyHash128GenByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamDbKeyHash128GenObj obj = readDbKeyHash128Gen( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash128GenObj readDbKeyHash128GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readDbKeyHash128GenByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamDbKeyHash128GenObj readDbKeyHash128GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamDbKeyHash128GenObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamDbKeyHash128GenObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamDbKeyHash128GenObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readDbKeyHash128GenByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash128GenByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash128GenObj realised = (ICFBamDbKeyHash128GenObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
		Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
		List<ICFBamDbKeyHash128GenObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readDbKeyHash128GenByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash128GenByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash128GenObj realised = (ICFBamDbKeyHash128GenObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
		Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
		List<ICFBamDbKeyHash128GenObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readDbKeyHash128GenByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash128GenByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash128GenObj realised = (ICFBamDbKeyHash128GenObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
		Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
		List<ICFBamDbKeyHash128GenObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readDbKeyHash128GenByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash128GenByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByNextIdx( null,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash128GenObj realised = (ICFBamDbKeyHash128GenObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
		Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
		List<ICFBamDbKeyHash128GenObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readDbKeyHash128GenByContPrevIdx( ScopeId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash128GenByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContPrevIdx( null,
				ScopeId,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash128GenObj realised = (ICFBamDbKeyHash128GenObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
		Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
		List<ICFBamDbKeyHash128GenObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		return( readDbKeyHash128GenByContNextIdx( ScopeId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash128GenByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContNextIdx( null,
				ScopeId,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash128GenObj realised = (ICFBamDbKeyHash128GenObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
		Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
		List<ICFBamDbKeyHash128GenObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		return( readDbKeyHash128GenBySchemaIdx( SchemaDefId,
			false ) );
	}

	@Override
	public List<ICFBamDbKeyHash128GenObj> readDbKeyHash128GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead )
	{
		final String S_ProcName = "readDbKeyHash128GenBySchemaIdx";
		ICFBamDbKeyHash128TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash128Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamDbKeyHash128TypeBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaIdx.containsKey( key ) ) {
			dict = indexBySchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj>();
			ICFBamDbKeyHash128TypeObj obj;
			ICFBamDbKeyHash128Type[] recList = schema.getCFBamBackingStore().getTableDbKeyHash128Type().readDerivedBySchemaIdx( null,
				SchemaDefId );
			ICFBamDbKeyHash128Type rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDbKeyHash128GenObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDbKeyHash128GenObj realised = (ICFBamDbKeyHash128GenObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
		Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
		List<ICFBamDbKeyHash128GenObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDbKeyHash128GenObj readCachedDbKeyHash128GenByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash128GenObj obj = null;
		obj = readCachedDbKeyHash128Gen( Id );
		return( obj );
	}

	@Override
	public ICFBamDbKeyHash128GenObj readCachedDbKeyHash128GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamDbKeyHash128GenObj obj = null;
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
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
			Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
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
	public List<ICFBamDbKeyHash128GenObj> readCachedDbKeyHash128GenByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedDbKeyHash128GenByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
				Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash128GenObj obj;
			Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
	public List<ICFBamDbKeyHash128GenObj> readCachedDbKeyHash128GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedDbKeyHash128GenByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
				Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash128GenObj obj;
			Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
	public List<ICFBamDbKeyHash128GenObj> readCachedDbKeyHash128GenByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDbKeyHash128GenByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
				Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash128GenObj obj;
			Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
	public List<ICFBamDbKeyHash128GenObj> readCachedDbKeyHash128GenByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDbKeyHash128GenByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
				Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash128GenObj obj;
			Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
	public List<ICFBamDbKeyHash128GenObj> readCachedDbKeyHash128GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDbKeyHash128GenByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>();
		if( indexByContPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
				Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash128GenObj obj;
			Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
	public List<ICFBamDbKeyHash128GenObj> readCachedDbKeyHash128GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDbKeyHash128GenByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>();
		if( indexByContNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
				Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash128GenObj obj;
			Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
	public List<ICFBamDbKeyHash128GenObj> readCachedDbKeyHash128GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "readCachedDbKeyHash128GenBySchemaIdx";
		ICFBamDbKeyHash128TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash128Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		ArrayList<ICFBamDbKeyHash128GenObj> arrayList = new ArrayList<ICFBamDbKeyHash128GenObj>();
		if( indexBySchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict;
			if( indexBySchemaIdx.containsKey( key ) ) {
				dict = indexBySchemaIdx.get( key );
				int len = dict.size();
				ICFBamDbKeyHash128GenObj arr[] = new ICFBamDbKeyHash128GenObj[len];
				Iterator<ICFBamDbKeyHash128GenObj> valIter = dict.values().iterator();
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
			ICFBamDbKeyHash128GenObj obj;
			Iterator<ICFBamDbKeyHash128GenObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDbKeyHash128GenObj> cmp = new Comparator<ICFBamDbKeyHash128GenObj>() {
			@Override
			public int compare( ICFBamDbKeyHash128GenObj lhs, ICFBamDbKeyHash128GenObj rhs ) {
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
	public void deepDisposeDbKeyHash128GenByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash128GenObj obj = readCachedDbKeyHash128GenByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDbKeyHash128GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamDbKeyHash128GenObj obj = readCachedDbKeyHash128GenByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDbKeyHash128GenByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash128GenByScopeIdx";
		ICFBamDbKeyHash128GenObj obj;
		List<ICFBamDbKeyHash128GenObj> arrayList = readCachedDbKeyHash128GenByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash128GenObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash128GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash128GenByDefSchemaIdx";
		ICFBamDbKeyHash128GenObj obj;
		List<ICFBamDbKeyHash128GenObj> arrayList = readCachedDbKeyHash128GenByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash128GenObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash128GenByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash128GenByPrevIdx";
		ICFBamDbKeyHash128GenObj obj;
		List<ICFBamDbKeyHash128GenObj> arrayList = readCachedDbKeyHash128GenByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash128GenObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash128GenByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash128GenByNextIdx";
		ICFBamDbKeyHash128GenObj obj;
		List<ICFBamDbKeyHash128GenObj> arrayList = readCachedDbKeyHash128GenByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash128GenObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash128GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash128GenByContPrevIdx";
		ICFBamDbKeyHash128GenObj obj;
		List<ICFBamDbKeyHash128GenObj> arrayList = readCachedDbKeyHash128GenByContPrevIdx( ScopeId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash128GenObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash128GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash128GenByContNextIdx";
		ICFBamDbKeyHash128GenObj obj;
		List<ICFBamDbKeyHash128GenObj> arrayList = readCachedDbKeyHash128GenByContNextIdx( ScopeId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash128GenObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDbKeyHash128GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "deepDisposeDbKeyHash128GenBySchemaIdx";
		ICFBamDbKeyHash128GenObj obj;
		List<ICFBamDbKeyHash128GenObj> arrayList = readCachedDbKeyHash128GenBySchemaIdx( SchemaDefId );
		if( arrayList != null )  {
			Iterator<ICFBamDbKeyHash128GenObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamDbKeyHash128GenObj updateDbKeyHash128Gen( ICFBamDbKeyHash128GenObj Obj ) {
		ICFBamDbKeyHash128GenObj obj = Obj;
		schema.getCFBamBackingStore().getTableDbKeyHash128Gen().updateDbKeyHash128Gen( null,
			Obj.getDbKeyHash128GenRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getDbKeyHash128GenTableObj().getClassCode() ) {
			obj = (ICFBamDbKeyHash128GenObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteDbKeyHash128Gen( ICFBamDbKeyHash128GenObj Obj ) {
		ICFBamDbKeyHash128GenObj obj = Obj;
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		ICFBamValueObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128Gen( null,
			obj.getDbKeyHash128GenRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteDbKeyHash128GenByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDbKeyHash128GenObj obj = readDbKeyHash128Gen(Id);
		if( obj != null ) {
			ICFBamDbKeyHash128GenEditObj editObj = (ICFBamDbKeyHash128GenEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamDbKeyHash128GenEditObj)obj.beginEdit();
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
		deepDisposeDbKeyHash128GenByIdIdx( Id );
	}

	@Override
	public void deleteDbKeyHash128GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamDbKeyHash128GenObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamDbKeyHash128GenObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeDbKeyHash128GenByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteDbKeyHash128GenByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamDbKeyHash128GenObj> iter = dict.values().iterator();
			ICFBamDbKeyHash128GenObj obj;
			List<ICFBamDbKeyHash128GenObj> toForget = new LinkedList<ICFBamDbKeyHash128GenObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByScopeIdx( null,
				ScopeId );
		}
		deepDisposeDbKeyHash128GenByScopeIdx( ScopeId );
	}

	@Override
	public void deleteDbKeyHash128GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamDbKeyHash128GenObj> iter = dict.values().iterator();
			ICFBamDbKeyHash128GenObj obj;
			List<ICFBamDbKeyHash128GenObj> toForget = new LinkedList<ICFBamDbKeyHash128GenObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeDbKeyHash128GenByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteDbKeyHash128GenByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByPrevIdx( null,
				PrevId );
			Iterator<ICFBamDbKeyHash128GenObj> iter = dict.values().iterator();
			ICFBamDbKeyHash128GenObj obj;
			List<ICFBamDbKeyHash128GenObj> toForget = new LinkedList<ICFBamDbKeyHash128GenObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByPrevIdx( null,
				PrevId );
		}
		deepDisposeDbKeyHash128GenByPrevIdx( PrevId );
	}

	@Override
	public void deleteDbKeyHash128GenByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByNextIdx( null,
				NextId );
			Iterator<ICFBamDbKeyHash128GenObj> iter = dict.values().iterator();
			ICFBamDbKeyHash128GenObj obj;
			List<ICFBamDbKeyHash128GenObj> toForget = new LinkedList<ICFBamDbKeyHash128GenObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByNextIdx( null,
				NextId );
		}
		deepDisposeDbKeyHash128GenByNextIdx( NextId );
	}

	@Override
	public void deleteDbKeyHash128GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByContPrevIdx( null,
				ScopeId,
				PrevId );
			Iterator<ICFBamDbKeyHash128GenObj> iter = dict.values().iterator();
			ICFBamDbKeyHash128GenObj obj;
			List<ICFBamDbKeyHash128GenObj> toForget = new LinkedList<ICFBamDbKeyHash128GenObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByContPrevIdx( null,
				ScopeId,
				PrevId );
		}
		deepDisposeDbKeyHash128GenByContPrevIdx( ScopeId,
				PrevId );
	}

	@Override
	public void deleteDbKeyHash128GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByContNextIdx( null,
				ScopeId,
				NextId );
			Iterator<ICFBamDbKeyHash128GenObj> iter = dict.values().iterator();
			ICFBamDbKeyHash128GenObj obj;
			List<ICFBamDbKeyHash128GenObj> toForget = new LinkedList<ICFBamDbKeyHash128GenObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenByContNextIdx( null,
				ScopeId,
				NextId );
		}
		deepDisposeDbKeyHash128GenByContNextIdx( ScopeId,
				NextId );
	}

	@Override
	public void deleteDbKeyHash128GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		ICFBamDbKeyHash128TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDbKeyHash128Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamDbKeyHash128TypeBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj > >();
		}
		if( indexBySchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDbKeyHash128GenObj> dict = indexBySchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenBySchemaIdx( null,
				SchemaDefId );
			Iterator<ICFBamDbKeyHash128GenObj> iter = dict.values().iterator();
			ICFBamDbKeyHash128GenObj obj;
			List<ICFBamDbKeyHash128GenObj> toForget = new LinkedList<ICFBamDbKeyHash128GenObj>();
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
			schema.getCFBamBackingStore().getTableDbKeyHash128Gen().deleteDbKeyHash128GenBySchemaIdx( null,
				SchemaDefId );
		}
		deepDisposeDbKeyHash128GenBySchemaIdx( SchemaDefId );
	}

	/**
	 *	Move the CFBamDbKeyHash128GenObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash128GenObj refreshed cache instance.
	 */
	@Override
	public ICFBamDbKeyHash128GenObj moveUpDbKeyHash128Gen( ICFBamDbKeyHash128GenObj Obj ) {
		ICFBamDbKeyHash128GenObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpDbKeyHash128Gen" );
		}
		ICFBamDbKeyHash128Gen rec = schema.getCFBamBackingStore().getTableDbKeyHash128Gen().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDbKeyHash128GenTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDbKeyHash128GenObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamValueObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamDbKeyHash128GenObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash128GenObj refreshed cache instance.
	 */
	@Override
	public ICFBamDbKeyHash128GenObj moveDownDbKeyHash128Gen( ICFBamDbKeyHash128GenObj Obj ) {
		ICFBamDbKeyHash128GenObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownDbKeyHash128Gen" );
		}
		ICFBamDbKeyHash128Gen rec = schema.getCFBamBackingStore().getTableDbKeyHash128Gen().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDbKeyHash128GenTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDbKeyHash128GenObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamValueObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}