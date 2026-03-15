// Description: Java 25 Table Object implementation for Int16Type.

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

public class CFBamInt16TypeTableObj
	implements ICFBamInt16TypeTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamInt16Type.CLASS_CODE;
	protected static final int backingClassCode = ICFBamInt16Type.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> members;
	private Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> allInt16Type;
	private Map< ICFBamValueByUNameIdxKey,
		ICFBamInt16TypeObj > indexByUNameIdx;
	private Map< ICFBamValueByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > > indexByScopeIdx;
	private Map< ICFBamValueByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > > indexByDefSchemaIdx;
	private Map< ICFBamValueByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > > indexByPrevIdx;
	private Map< ICFBamValueByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > > indexByNextIdx;
	private Map< ICFBamValueByContPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > > indexByContPrevIdx;
	private Map< ICFBamValueByContNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > > indexByContNextIdx;
	private Map< ICFBamInt16TypeBySchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > > indexBySchemaIdx;
	public static String TABLE_NAME = "Int16Type";
	public static String TABLE_DBNAME = "int16typ";

	public CFBamInt16TypeTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
		allInt16Type = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
	}

	public CFBamInt16TypeTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
		allInt16Type = null;
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
		return CFBamInt16TypeTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamInt16TypeTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allInt16Type = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexBySchemaIdx = null;
		List<ICFBamInt16TypeObj> toForget = new LinkedList<ICFBamInt16TypeObj>();
		ICFBamInt16TypeObj cur = null;
		Iterator<ICFBamInt16TypeObj> iter = members.values().iterator();
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
	 *	CFBamInt16TypeObj.
	 */
	@Override
	public ICFBamInt16TypeObj newInstance() {
		ICFBamInt16TypeObj inst = new CFBamInt16TypeObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamInt16TypeObj.
	 */
	@Override
	public ICFBamInt16TypeEditObj newEditInstance( ICFBamInt16TypeObj orig ) {
		ICFBamInt16TypeEditObj edit = new CFBamInt16TypeEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamInt16TypeObj realiseInt16Type( ICFBamInt16TypeObj Obj ) {
		ICFBamInt16TypeObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamInt16TypeObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamInt16TypeObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamInt16TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryInt16Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
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
			keepObj = (ICFBamInt16TypeObj)schema.getInt16DefTableObj().realiseInt16Def( Obj );

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
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamInt16TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryInt16Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allInt16Type != null ) {
				allInt16Type.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamInt16TypeObj)schema.getInt16DefTableObj().realiseInt16Def( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allInt16Type != null ) {
				allInt16Type.put( keepObj.getPKey(), keepObj );
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
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamInt16TypeBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactoryInt16Type().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamInt16TypeObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamInt16TypeObj createInt16Type( ICFBamInt16TypeObj Obj ) {
		ICFBamInt16TypeObj obj = Obj;
		ICFBamInt16Type rec = obj.getInt16TypeRec();
		schema.getCFBamBackingStore().getTableInt16Type().createInt16Type(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamInt16TypeObj)(obj.realise());
		}
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamInt16TypeObj readInt16Type( CFLibDbKeyHash256 pkey ) {
		return( readInt16Type( pkey, false ) );
	}

	@Override
	public ICFBamInt16TypeObj readInt16Type( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamInt16TypeObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamInt16Type readRec = schema.getCFBamBackingStore().getTableInt16Type().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamInt16TypeObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamInt16TypeObj readCachedInt16Type( CFLibDbKeyHash256 pkey ) {
		ICFBamInt16TypeObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeInt16Type( ICFBamInt16TypeObj obj )
	{
		final String S_ProcName = "CFBamInt16TypeTableObj.reallyDeepDisposeInt16Type() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamInt16TypeObj existing = readCachedInt16Type( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamInt16TypeBySchemaIdxKey keySchemaIdx = schema.getCFBamBackingStore().getFactoryInt16Type().newBySchemaIdxKey();
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


		schema.getInt16DefTableObj().reallyDeepDisposeInt16Def( obj );
	}
	@Override
	public void deepDisposeInt16Type( CFLibDbKeyHash256 pkey ) {
		ICFBamInt16TypeObj obj = readCachedInt16Type( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamInt16TypeObj lockInt16Type( CFLibDbKeyHash256 pkey ) {
		ICFBamInt16TypeObj locked = null;
		ICFBamInt16Type lockRec = schema.getCFBamBackingStore().getTableInt16Type().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamInt16TypeObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockInt16Type", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamInt16TypeObj> readAllInt16Type() {
		return( readAllInt16Type( false ) );
	}

	@Override
	public List<ICFBamInt16TypeObj> readAllInt16Type( boolean forceRead ) {
		final String S_ProcName = "readAllInt16Type";
		if( ( allInt16Type == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> map = new HashMap<CFLibDbKeyHash256,ICFBamInt16TypeObj>();
			allInt16Type = map;
			ICFBamInt16Type[] recList = schema.getCFBamBackingStore().getTableInt16Type().readAllDerived( null );
			ICFBamInt16Type rec;
			ICFBamInt16TypeObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamInt16TypeObj realised = (ICFBamInt16TypeObj)obj.realise();
			}
		}
		int len = allInt16Type.size();
		ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
		Iterator<ICFBamInt16TypeObj> valIter = allInt16Type.values().iterator();
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
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
		List<ICFBamInt16TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamInt16TypeObj> readCachedAllInt16Type() {
		final String S_ProcName = "readCachedAllInt16Type";
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>();
		if( allInt16Type != null ) {
			int len = allInt16Type.size();
			ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
			Iterator<ICFBamInt16TypeObj> valIter = allInt16Type.values().iterator();
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
		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
	public ICFBamInt16TypeObj readInt16TypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readInt16TypeByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamInt16TypeObj readInt16TypeByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamInt16TypeObj obj = readInt16Type( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamInt16TypeObj readInt16TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readInt16TypeByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamInt16TypeObj readInt16TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamInt16TypeObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamInt16TypeObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamInt16TypeObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readInt16TypeByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readInt16TypeByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamInt16TypeObj realised = (ICFBamInt16TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
		Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
		List<ICFBamInt16TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readInt16TypeByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readInt16TypeByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamInt16TypeObj realised = (ICFBamInt16TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
		Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
		List<ICFBamInt16TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readInt16TypeByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readInt16TypeByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamInt16TypeObj realised = (ICFBamInt16TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
		Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
		List<ICFBamInt16TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readInt16TypeByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readInt16TypeByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByNextIdx( null,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamInt16TypeObj realised = (ICFBamInt16TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
		Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
		List<ICFBamInt16TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readInt16TypeByContPrevIdx( ScopeId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readInt16TypeByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContPrevIdx( null,
				ScopeId,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamInt16TypeObj realised = (ICFBamInt16TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
		Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
		List<ICFBamInt16TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		return( readInt16TypeByContNextIdx( ScopeId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readInt16TypeByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContNextIdx( null,
				ScopeId,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamInt16TypeObj realised = (ICFBamInt16TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
		Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
		List<ICFBamInt16TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		return( readInt16TypeBySchemaIdx( SchemaDefId,
			false ) );
	}

	@Override
	public List<ICFBamInt16TypeObj> readInt16TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead )
	{
		final String S_ProcName = "readInt16TypeBySchemaIdx";
		ICFBamInt16TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryInt16Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamInt16TypeBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaIdx.containsKey( key ) ) {
			dict = indexBySchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamInt16TypeObj>();
			ICFBamInt16TypeObj obj;
			ICFBamInt16Type[] recList = schema.getCFBamBackingStore().getTableInt16Type().readDerivedBySchemaIdx( null,
				SchemaDefId );
			ICFBamInt16Type rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamInt16TypeObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamInt16TypeObj realised = (ICFBamInt16TypeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
		Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
		List<ICFBamInt16TypeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamInt16TypeObj readCachedInt16TypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamInt16TypeObj obj = null;
		obj = readCachedInt16Type( Id );
		return( obj );
	}

	@Override
	public ICFBamInt16TypeObj readCachedInt16TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamInt16TypeObj obj = null;
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
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
			Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
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
	public List<ICFBamInt16TypeObj> readCachedInt16TypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedInt16TypeByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
				Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
			ICFBamInt16TypeObj obj;
			Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
	public List<ICFBamInt16TypeObj> readCachedInt16TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedInt16TypeByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
				Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
			ICFBamInt16TypeObj obj;
			Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
	public List<ICFBamInt16TypeObj> readCachedInt16TypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedInt16TypeByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
				Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
			ICFBamInt16TypeObj obj;
			Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
	public List<ICFBamInt16TypeObj> readCachedInt16TypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedInt16TypeByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
				Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
			ICFBamInt16TypeObj obj;
			Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
	public List<ICFBamInt16TypeObj> readCachedInt16TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedInt16TypeByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>();
		if( indexByContPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
				Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
			ICFBamInt16TypeObj obj;
			Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
	public List<ICFBamInt16TypeObj> readCachedInt16TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedInt16TypeByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>();
		if( indexByContNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
				Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
			ICFBamInt16TypeObj obj;
			Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
	public List<ICFBamInt16TypeObj> readCachedInt16TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "readCachedInt16TypeBySchemaIdx";
		ICFBamInt16TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryInt16Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		ArrayList<ICFBamInt16TypeObj> arrayList = new ArrayList<ICFBamInt16TypeObj>();
		if( indexBySchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict;
			if( indexBySchemaIdx.containsKey( key ) ) {
				dict = indexBySchemaIdx.get( key );
				int len = dict.size();
				ICFBamInt16TypeObj arr[] = new ICFBamInt16TypeObj[len];
				Iterator<ICFBamInt16TypeObj> valIter = dict.values().iterator();
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
			ICFBamInt16TypeObj obj;
			Iterator<ICFBamInt16TypeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamInt16TypeObj> cmp = new Comparator<ICFBamInt16TypeObj>() {
			@Override
			public int compare( ICFBamInt16TypeObj lhs, ICFBamInt16TypeObj rhs ) {
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
	public void deepDisposeInt16TypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamInt16TypeObj obj = readCachedInt16TypeByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeInt16TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamInt16TypeObj obj = readCachedInt16TypeByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeInt16TypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeInt16TypeByScopeIdx";
		ICFBamInt16TypeObj obj;
		List<ICFBamInt16TypeObj> arrayList = readCachedInt16TypeByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamInt16TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeInt16TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeInt16TypeByDefSchemaIdx";
		ICFBamInt16TypeObj obj;
		List<ICFBamInt16TypeObj> arrayList = readCachedInt16TypeByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamInt16TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeInt16TypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeInt16TypeByPrevIdx";
		ICFBamInt16TypeObj obj;
		List<ICFBamInt16TypeObj> arrayList = readCachedInt16TypeByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamInt16TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeInt16TypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeInt16TypeByNextIdx";
		ICFBamInt16TypeObj obj;
		List<ICFBamInt16TypeObj> arrayList = readCachedInt16TypeByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamInt16TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeInt16TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeInt16TypeByContPrevIdx";
		ICFBamInt16TypeObj obj;
		List<ICFBamInt16TypeObj> arrayList = readCachedInt16TypeByContPrevIdx( ScopeId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamInt16TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeInt16TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeInt16TypeByContNextIdx";
		ICFBamInt16TypeObj obj;
		List<ICFBamInt16TypeObj> arrayList = readCachedInt16TypeByContNextIdx( ScopeId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamInt16TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeInt16TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "deepDisposeInt16TypeBySchemaIdx";
		ICFBamInt16TypeObj obj;
		List<ICFBamInt16TypeObj> arrayList = readCachedInt16TypeBySchemaIdx( SchemaDefId );
		if( arrayList != null )  {
			Iterator<ICFBamInt16TypeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamInt16TypeObj updateInt16Type( ICFBamInt16TypeObj Obj ) {
		ICFBamInt16TypeObj obj = Obj;
		schema.getCFBamBackingStore().getTableInt16Type().updateInt16Type( null,
			Obj.getInt16TypeRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getInt16TypeTableObj().getClassCode() ) {
			obj = (ICFBamInt16TypeObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteInt16Type( ICFBamInt16TypeObj Obj ) {
		ICFBamInt16TypeObj obj = Obj;
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		ICFBamValueObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableInt16Type().deleteInt16Type( null,
			obj.getInt16TypeRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteInt16TypeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamInt16TypeObj obj = readInt16Type(Id);
		if( obj != null ) {
			ICFBamInt16TypeEditObj editObj = (ICFBamInt16TypeEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamInt16TypeEditObj)obj.beginEdit();
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
		deepDisposeInt16TypeByIdIdx( Id );
	}

	@Override
	public void deleteInt16TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamInt16TypeObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamInt16TypeObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeInt16TypeByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteInt16TypeByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamInt16TypeObj> iter = dict.values().iterator();
			ICFBamInt16TypeObj obj;
			List<ICFBamInt16TypeObj> toForget = new LinkedList<ICFBamInt16TypeObj>();
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
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByScopeIdx( null,
				ScopeId );
		}
		deepDisposeInt16TypeByScopeIdx( ScopeId );
	}

	@Override
	public void deleteInt16TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamInt16TypeObj> iter = dict.values().iterator();
			ICFBamInt16TypeObj obj;
			List<ICFBamInt16TypeObj> toForget = new LinkedList<ICFBamInt16TypeObj>();
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
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeInt16TypeByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteInt16TypeByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByPrevIdx( null,
				PrevId );
			Iterator<ICFBamInt16TypeObj> iter = dict.values().iterator();
			ICFBamInt16TypeObj obj;
			List<ICFBamInt16TypeObj> toForget = new LinkedList<ICFBamInt16TypeObj>();
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
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByPrevIdx( null,
				PrevId );
		}
		deepDisposeInt16TypeByPrevIdx( PrevId );
	}

	@Override
	public void deleteInt16TypeByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByNextIdx( null,
				NextId );
			Iterator<ICFBamInt16TypeObj> iter = dict.values().iterator();
			ICFBamInt16TypeObj obj;
			List<ICFBamInt16TypeObj> toForget = new LinkedList<ICFBamInt16TypeObj>();
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
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByNextIdx( null,
				NextId );
		}
		deepDisposeInt16TypeByNextIdx( NextId );
	}

	@Override
	public void deleteInt16TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByContPrevIdx( null,
				ScopeId,
				PrevId );
			Iterator<ICFBamInt16TypeObj> iter = dict.values().iterator();
			ICFBamInt16TypeObj obj;
			List<ICFBamInt16TypeObj> toForget = new LinkedList<ICFBamInt16TypeObj>();
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
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByContPrevIdx( null,
				ScopeId,
				PrevId );
		}
		deepDisposeInt16TypeByContPrevIdx( ScopeId,
				PrevId );
	}

	@Override
	public void deleteInt16TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByContNextIdx( null,
				ScopeId,
				NextId );
			Iterator<ICFBamInt16TypeObj> iter = dict.values().iterator();
			ICFBamInt16TypeObj obj;
			List<ICFBamInt16TypeObj> toForget = new LinkedList<ICFBamInt16TypeObj>();
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
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeByContNextIdx( null,
				ScopeId,
				NextId );
		}
		deepDisposeInt16TypeByContNextIdx( ScopeId,
				NextId );
	}

	@Override
	public void deleteInt16TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		ICFBamInt16TypeBySchemaIdxKey key = schema.getCFBamBackingStore().getFactoryInt16Type().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamInt16TypeBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamInt16TypeObj > >();
		}
		if( indexBySchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamInt16TypeObj> dict = indexBySchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeBySchemaIdx( null,
				SchemaDefId );
			Iterator<ICFBamInt16TypeObj> iter = dict.values().iterator();
			ICFBamInt16TypeObj obj;
			List<ICFBamInt16TypeObj> toForget = new LinkedList<ICFBamInt16TypeObj>();
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
			schema.getCFBamBackingStore().getTableInt16Type().deleteInt16TypeBySchemaIdx( null,
				SchemaDefId );
		}
		deepDisposeInt16TypeBySchemaIdx( SchemaDefId );
	}

	/**
	 *	Move the CFBamInt16TypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamInt16TypeObj refreshed cache instance.
	 */
	@Override
	public ICFBamInt16TypeObj moveUpInt16Type( ICFBamInt16TypeObj Obj ) {
		ICFBamInt16TypeObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpInt16Type" );
		}
		ICFBamInt16Type rec = schema.getCFBamBackingStore().getTableInt16Type().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getInt16TypeTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamInt16TypeObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamValueObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamInt16TypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamInt16TypeObj refreshed cache instance.
	 */
	@Override
	public ICFBamInt16TypeObj moveDownInt16Type( ICFBamInt16TypeObj Obj ) {
		ICFBamInt16TypeObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownInt16Type" );
		}
		ICFBamInt16Type rec = schema.getCFBamBackingStore().getTableInt16Type().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getInt16TypeTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamInt16TypeObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamValueObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}