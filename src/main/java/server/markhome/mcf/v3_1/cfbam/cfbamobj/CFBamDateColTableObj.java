// Description: Java 25 Table Object implementation for DateCol.

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
import server.markhome.mcf.v3_1.cflib.keyhash.*;

import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

public class CFBamDateColTableObj
	implements ICFBamDateColTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamDateCol.CLASS_CODE;
	protected static final int backingClassCode = ICFBamDateCol.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> allDateCol;
	private Map< ICFBamValueByUNameIdxKey,
		ICFBamDateColObj > indexByUNameIdx;
	private Map< ICFBamValueByScopeIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > > indexByScopeIdx;
	private Map< ICFBamValueByDefSchemaIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > > indexByDefSchemaIdx;
	private Map< ICFBamValueByPrevIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > > indexByPrevIdx;
	private Map< ICFBamValueByNextIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > > indexByNextIdx;
	private Map< ICFBamValueByContPrevIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > > indexByContPrevIdx;
	private Map< ICFBamValueByContNextIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > > indexByContNextIdx;
	private Map< ICFBamDateColByTableIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > > indexByTableIdx;
	public static String TABLE_NAME = "DateCol";
	public static String TABLE_DBNAME = "dtcol";

	public CFBamDateColTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
		allDateCol = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexByTableIdx = null;
	}

	public CFBamDateColTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
		allDateCol = null;
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
		return CFBamDateColTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamDateColTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allDateCol = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		indexByTableIdx = null;
		List<ICFBamDateColObj> toForget = new LinkedList<ICFBamDateColObj>();
		ICFBamDateColObj cur = null;
		Iterator<ICFBamDateColObj> iter = members.values().iterator();
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
	 *	CFBamDateColObj.
	 */
	@Override
	public ICFBamDateColObj newInstance() {
		ICFBamDateColObj inst = new CFBamDateColObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDateColObj.
	 */
	@Override
	public ICFBamDateColEditObj newEditInstance( ICFBamDateColObj orig ) {
		ICFBamDateColEditObj edit = new CFBamDateColEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamDateColObj realiseDateCol( ICFBamDateColObj Obj ) {
		ICFBamDateColObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFBamDateColObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamDateColObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamDateColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryDateCol().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
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
			keepObj = (ICFBamDateColObj)schema.getDateDefTableObj().realiseDateDef( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamDateColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryDateCol().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allDateCol != null ) {
				allDateCol.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamDateColObj)schema.getDateDefTableObj().realiseDateDef( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allDateCol != null ) {
				allDateCol.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByTableIdx != null ) {
				ICFBamDateColByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getCFBamFactory().getFactoryDateCol().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamDateColObj createDateCol( ICFBamDateColObj Obj ) {
		ICFBamDateColObj obj = Obj;
		ICFBamDateCol rec = obj.getDateColRec();
		schema.getCFBamBackingStore().getTableDateCol().createDateCol(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamDateColObj)(obj.realise());
		}
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamDateColObj readDateCol( $implCommaIJavaOptAtomType$ pkey ) {
		return( readDateCol( pkey, false ) );
	}

	@Override
	public ICFBamDateColObj readDateCol( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFBamDateColObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamDateCol readRec = schema.getCFBamBackingStore().getTableDateCol().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamDateColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDateColObj readCachedDateCol( $implCommaIJavaOptAtomType$ pkey ) {
		ICFBamDateColObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeDateCol( ICFBamDateColObj obj )
	{
		final String S_ProcName = "CFBamDateColTableObj.reallyDeepDisposeDateCol() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFBamDateColObj existing = readCachedDateCol( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamDateColByTableIdxKey keyTableIdx = schema.getCFBamBackingStore().getCFBamFactory().getFactoryDateCol().newByTableIdxKey();
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


		schema.getDateDefTableObj().reallyDeepDisposeDateDef( obj );
	}
	@Override
	public void deepDisposeDateCol( $implCommaIJavaOptAtomType$ pkey ) {
		ICFBamDateColObj obj = readCachedDateCol( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDateColObj lockDateCol( $implCommaIJavaOptAtomType$ pkey ) {
		ICFBamDateColObj locked = null;
		ICFBamDateCol lockRec = schema.getCFBamBackingStore().getTableDateCol().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamDateColObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockDateCol", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamDateColObj> readAllDateCol() {
		return( readAllDateCol( false ) );
	}

	@Override
	public List<ICFBamDateColObj> readAllDateCol( boolean forceRead ) {
		final String S_ProcName = "readAllDateCol";
		if( ( allDateCol == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFBamDateColObj>();
			allDateCol = map;
			ICFBamDateCol[] recList = schema.getCFBamBackingStore().getTableDateCol().readAllDerived( null );
			ICFBamDateCol rec;
			ICFBamDateColObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDateColObj realised = (ICFBamDateColObj)obj.realise();
			}
		}
		int len = allDateCol.size();
		ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
		Iterator<ICFBamDateColObj> valIter = allDateCol.values().iterator();
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
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDateColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDateColObj> readCachedAllDateCol() {
		final String S_ProcName = "readCachedAllDateCol";
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>();
		if( allDateCol != null ) {
			int len = allDateCol.size();
			ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
			Iterator<ICFBamDateColObj> valIter = allDateCol.values().iterator();
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
		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamDateColObj readDateColByIdIdx( ICFLibKeyHash256 Id )
	{
		return( readDateColByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamDateColObj readDateColByIdIdx( ICFLibKeyHash256 Id, boolean forceRead )
	{
		ICFBamDateColObj obj = readDateCol( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamDateColObj readDateColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name )
	{
		return( readDateColByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamDateColObj readDateColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamDateColObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamDateColObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamDateColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByScopeIdx( ICFLibKeyHash256 ScopeId )
	{
		return( readDateColByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readDateColByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDateColObj realised = (ICFBamDateColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
		Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDateColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId )
	{
		return( readDateColByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readDateColByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDateColObj realised = (ICFBamDateColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
		Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDateColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByPrevIdx( ICFLibKeyHash256 PrevId )
	{
		return( readDateColByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDateColByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDateColObj realised = (ICFBamDateColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
		Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDateColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByNextIdx( ICFLibKeyHash256 NextId )
	{
		return( readDateColByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDateColByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByNextIdx( null,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDateColObj realised = (ICFBamDateColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
		Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDateColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId )
	{
		return( readDateColByContPrevIdx( ScopeId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDateColByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContPrevIdx( null,
				ScopeId,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDateColObj realised = (ICFBamDateColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
		Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDateColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId )
	{
		return( readDateColByContNextIdx( ScopeId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDateColByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContNextIdx( null,
				ScopeId,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDateColObj realised = (ICFBamDateColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
		Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDateColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByTableIdx( ICFLibKeyHash256 TableId )
	{
		return( readDateColByTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamDateColObj> readDateColByTableIdx( ICFLibKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readDateColByTableIdx";
		ICFBamDateColByTableIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryDateCol().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
		if( indexByTableIdx == null ) {
			indexByTableIdx = new HashMap< ICFBamDateColByTableIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( ( ! forceRead ) && indexByTableIdx.containsKey( key ) ) {
			dict = indexByTableIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFBamDateColObj>();
			ICFBamDateColObj obj;
			ICFBamDateCol[] recList = schema.getCFBamBackingStore().getTableDateCol().readDerivedByTableIdx( null,
				TableId );
			ICFBamDateCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDateColObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDateColObj realised = (ICFBamDateColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
		Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDateColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDateColObj readCachedDateColByIdIdx( ICFLibKeyHash256 Id )
	{
		ICFBamDateColObj obj = null;
		obj = readCachedDateCol( Id );
		return( obj );
	}

	@Override
	public ICFBamDateColObj readCachedDateColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name )
	{
		ICFBamDateColObj obj = null;
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamDateColObj> valIter = members.values().iterator();
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
			Iterator<ICFBamDateColObj> valIter = members.values().iterator();
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
	public List<ICFBamDateColObj> readCachedDateColByScopeIdx( ICFLibKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedDateColByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>();
		if( indexByScopeIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
				Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
			ICFBamDateColObj obj;
			Iterator<ICFBamDateColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDateColObj> readCachedDateColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedDateColByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
				Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
			ICFBamDateColObj obj;
			Iterator<ICFBamDateColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDateColObj> readCachedDateColByPrevIdx( ICFLibKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDateColByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>();
		if( indexByPrevIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
				Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
			ICFBamDateColObj obj;
			Iterator<ICFBamDateColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDateColObj> readCachedDateColByNextIdx( ICFLibKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDateColByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>();
		if( indexByNextIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
				Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
			ICFBamDateColObj obj;
			Iterator<ICFBamDateColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDateColObj> readCachedDateColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDateColByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>();
		if( indexByContPrevIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
				Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
			ICFBamDateColObj obj;
			Iterator<ICFBamDateColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDateColObj> readCachedDateColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDateColByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>();
		if( indexByContNextIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
				Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
			ICFBamDateColObj obj;
			Iterator<ICFBamDateColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDateColObj> readCachedDateColByTableIdx( ICFLibKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedDateColByTableIdx";
		ICFBamDateColByTableIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryDateCol().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamDateColObj> arrayList = new ArrayList<ICFBamDateColObj>();
		if( indexByTableIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict;
			if( indexByTableIdx.containsKey( key ) ) {
				dict = indexByTableIdx.get( key );
				int len = dict.size();
				ICFBamDateColObj arr[] = new ICFBamDateColObj[len];
				Iterator<ICFBamDateColObj> valIter = dict.values().iterator();
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
			ICFBamDateColObj obj;
			Iterator<ICFBamDateColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDateColObj> cmp = new Comparator<ICFBamDateColObj>() {
			@Override
			public int compare( ICFBamDateColObj lhs, ICFBamDateColObj rhs ) {
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
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeDateColByIdIdx( ICFLibKeyHash256 Id )
	{
		ICFBamDateColObj obj = readCachedDateColByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDateColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name )
	{
		ICFBamDateColObj obj = readCachedDateColByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDateColByScopeIdx( ICFLibKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeDateColByScopeIdx";
		ICFBamDateColObj obj;
		List<ICFBamDateColObj> arrayList = readCachedDateColByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamDateColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDateColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeDateColByDefSchemaIdx";
		ICFBamDateColObj obj;
		List<ICFBamDateColObj> arrayList = readCachedDateColByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamDateColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDateColByPrevIdx( ICFLibKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDateColByPrevIdx";
		ICFBamDateColObj obj;
		List<ICFBamDateColObj> arrayList = readCachedDateColByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDateColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDateColByNextIdx( ICFLibKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDateColByNextIdx";
		ICFBamDateColObj obj;
		List<ICFBamDateColObj> arrayList = readCachedDateColByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDateColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDateColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDateColByContPrevIdx";
		ICFBamDateColObj obj;
		List<ICFBamDateColObj> arrayList = readCachedDateColByContPrevIdx( ScopeId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDateColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDateColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDateColByContNextIdx";
		ICFBamDateColObj obj;
		List<ICFBamDateColObj> arrayList = readCachedDateColByContNextIdx( ScopeId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDateColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDateColByTableIdx( ICFLibKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeDateColByTableIdx";
		ICFBamDateColObj obj;
		List<ICFBamDateColObj> arrayList = readCachedDateColByTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamDateColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamDateColObj updateDateCol( ICFBamDateColObj Obj ) {
		ICFBamDateColObj obj = Obj;
		schema.getCFBamBackingStore().getTableDateCol().updateDateCol( null,
			Obj.getDateColRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getDateColTableObj().getClassCode() ) {
			obj = (ICFBamDateColObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteDateCol( ICFBamDateColObj Obj ) {
		ICFBamDateColObj obj = Obj;
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		ICFBamValueObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableDateCol().deleteDateCol( null,
			obj.getDateColRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteDateColByIdIdx( ICFLibKeyHash256 Id )
	{
		ICFBamDateColObj obj = readDateCol(Id);
		if( obj != null ) {
			ICFBamDateColEditObj editObj = (ICFBamDateColEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamDateColEditObj)obj.beginEdit();
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
		deepDisposeDateColByIdIdx( Id );
	}

	@Override
	public void deleteDateColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamDateColObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamDateColObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeDateColByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteDateColByScopeIdx( ICFLibKeyHash256 ScopeId )
	{
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamDateColObj> iter = dict.values().iterator();
			ICFBamDateColObj obj;
			List<ICFBamDateColObj> toForget = new LinkedList<ICFBamDateColObj>();
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
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByScopeIdx( null,
				ScopeId );
		}
		deepDisposeDateColByScopeIdx( ScopeId );
	}

	@Override
	public void deleteDateColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId )
	{
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamDateColObj> iter = dict.values().iterator();
			ICFBamDateColObj obj;
			List<ICFBamDateColObj> toForget = new LinkedList<ICFBamDateColObj>();
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
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeDateColByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteDateColByPrevIdx( ICFLibKeyHash256 PrevId )
	{
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByPrevIdx( null,
				PrevId );
			Iterator<ICFBamDateColObj> iter = dict.values().iterator();
			ICFBamDateColObj obj;
			List<ICFBamDateColObj> toForget = new LinkedList<ICFBamDateColObj>();
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
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByPrevIdx( null,
				PrevId );
		}
		deepDisposeDateColByPrevIdx( PrevId );
	}

	@Override
	public void deleteDateColByNextIdx( ICFLibKeyHash256 NextId )
	{
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByNextIdx( null,
				NextId );
			Iterator<ICFBamDateColObj> iter = dict.values().iterator();
			ICFBamDateColObj obj;
			List<ICFBamDateColObj> toForget = new LinkedList<ICFBamDateColObj>();
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
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByNextIdx( null,
				NextId );
		}
		deepDisposeDateColByNextIdx( NextId );
	}

	@Override
	public void deleteDateColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId )
	{
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByContPrevIdx( null,
				ScopeId,
				PrevId );
			Iterator<ICFBamDateColObj> iter = dict.values().iterator();
			ICFBamDateColObj obj;
			List<ICFBamDateColObj> toForget = new LinkedList<ICFBamDateColObj>();
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
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByContPrevIdx( null,
				ScopeId,
				PrevId );
		}
		deepDisposeDateColByContPrevIdx( ScopeId,
				PrevId );
	}

	@Override
	public void deleteDateColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId )
	{
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByContNextIdx( null,
				ScopeId,
				NextId );
			Iterator<ICFBamDateColObj> iter = dict.values().iterator();
			ICFBamDateColObj obj;
			List<ICFBamDateColObj> toForget = new LinkedList<ICFBamDateColObj>();
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
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByContNextIdx( null,
				ScopeId,
				NextId );
		}
		deepDisposeDateColByContNextIdx( ScopeId,
				NextId );
	}

	@Override
	public void deleteDateColByTableIdx( ICFLibKeyHash256 TableId )
	{
		ICFBamDateColByTableIdxKey key = schema.getCFBamBackingStore().getCFBamFactory().getFactoryDateCol().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByTableIdx == null ) {
			indexByTableIdx = new HashMap< ICFBamDateColByTableIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFBamDateColObj > >();
		}
		if( indexByTableIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFBamDateColObj> dict = indexByTableIdx.get( key );
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByTableIdx( null,
				TableId );
			Iterator<ICFBamDateColObj> iter = dict.values().iterator();
			ICFBamDateColObj obj;
			List<ICFBamDateColObj> toForget = new LinkedList<ICFBamDateColObj>();
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
			schema.getCFBamBackingStore().getTableDateCol().deleteDateColByTableIdx( null,
				TableId );
		}
		deepDisposeDateColByTableIdx( TableId );
	}

	/**
	 *	Move the CFBamDateColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDateColObj refreshed cache instance.
	 */
	@Override
	public ICFBamDateColObj moveUpDateCol( ICFBamDateColObj Obj ) {
		ICFBamDateColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpDateCol" );
		}
		ICFBamDateCol rec = schema.getCFBamBackingStore().getTableDateCol().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDateColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDateColObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamValueObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamDateColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDateColObj refreshed cache instance.
	 */
	@Override
	public ICFBamDateColObj moveDownDateCol( ICFBamDateColObj Obj ) {
		ICFBamDateColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownDateCol" );
		}
		ICFBamDateCol rec = schema.getCFBamBackingStore().getTableDateCol().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDateColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDateColObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamValueObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}