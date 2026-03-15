// Description: Java 25 Table Object implementation for IndexCol.

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

public class CFBamIndexColTableObj
	implements ICFBamIndexColTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamIndexCol.CLASS_CODE;
	protected static final int backingClassCode = ICFBamIndexCol.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamIndexColObj> members;
	private Map<CFLibDbKeyHash256, ICFBamIndexColObj> allIndexCol;
	private Map< ICFBamIndexColByUNameIdxKey,
		ICFBamIndexColObj > indexByUNameIdx;
	private Map< ICFBamIndexColByIndexIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexColObj > > indexByIndexIdx;
	private Map< ICFBamIndexColByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexColObj > > indexByDefSchemaIdx;
	private Map< ICFBamIndexColByColIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexColObj > > indexByColIdx;
	private Map< ICFBamIndexColByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexColObj > > indexByPrevIdx;
	private Map< ICFBamIndexColByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexColObj > > indexByNextIdx;
	private Map< ICFBamIndexColByIdxPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexColObj > > indexByIdxPrevIdx;
	private Map< ICFBamIndexColByIdxNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexColObj > > indexByIdxNextIdx;
	public static String TABLE_NAME = "IndexCol";
	public static String TABLE_DBNAME = "idxcol";

	public CFBamIndexColTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
		allIndexCol = null;
		indexByUNameIdx = null;
		indexByIndexIdx = null;
		indexByDefSchemaIdx = null;
		indexByColIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByIdxPrevIdx = null;
		indexByIdxNextIdx = null;
	}

	public CFBamIndexColTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
		allIndexCol = null;
		indexByUNameIdx = null;
		indexByIndexIdx = null;
		indexByDefSchemaIdx = null;
		indexByColIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByIdxPrevIdx = null;
		indexByIdxNextIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamIndexColTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamIndexColTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allIndexCol = null;
		indexByUNameIdx = null;
		indexByIndexIdx = null;
		indexByDefSchemaIdx = null;
		indexByColIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByIdxPrevIdx = null;
		indexByIdxNextIdx = null;
		List<ICFBamIndexColObj> toForget = new LinkedList<ICFBamIndexColObj>();
		ICFBamIndexColObj cur = null;
		Iterator<ICFBamIndexColObj> iter = members.values().iterator();
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
	 *	CFBamIndexColObj.
	 */
	@Override
	public ICFBamIndexColObj newInstance() {
		ICFBamIndexColObj inst = new CFBamIndexColObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamIndexColObj.
	 */
	@Override
	public ICFBamIndexColEditObj newEditInstance( ICFBamIndexColObj orig ) {
		ICFBamIndexColEditObj edit = new CFBamIndexColEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamIndexColObj realiseIndexCol( ICFBamIndexColObj Obj ) {
		ICFBamIndexColObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamIndexColObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamIndexColObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamIndexColByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByUNameIdxKey();
				keyUNameIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByIndexIdx != null ) {
				ICFBamIndexColByIndexIdxKey keyIndexIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIndexIdxKey();
				keyIndexIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIndexIdx = indexByIndexIdx.get( keyIndexIdx );
				if( mapIndexIdx != null ) {
					mapIndexIdx.remove( keepObj.getPKey() );
					if( mapIndexIdx.size() <= 0 ) {
						indexByIndexIdx.remove( keyIndexIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamIndexColByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByColIdx != null ) {
				ICFBamIndexColByColIdxKey keyColIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByColIdxKey();
				keyColIdx.setRequiredColumnId( keepObj.getRequiredColumnId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapColIdx = indexByColIdx.get( keyColIdx );
				if( mapColIdx != null ) {
					mapColIdx.remove( keepObj.getPKey() );
					if( mapColIdx.size() <= 0 ) {
						indexByColIdx.remove( keyColIdx );
					}
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamIndexColByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.remove( keepObj.getPKey() );
					if( mapPrevIdx.size() <= 0 ) {
						indexByPrevIdx.remove( keyPrevIdx );
					}
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamIndexColByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.remove( keepObj.getPKey() );
					if( mapNextIdx.size() <= 0 ) {
						indexByNextIdx.remove( keyNextIdx );
					}
				}
			}

			if( indexByIdxPrevIdx != null ) {
				ICFBamIndexColByIdxPrevIdxKey keyIdxPrevIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxPrevIdxKey();
				keyIdxPrevIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyIdxPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIdxPrevIdx = indexByIdxPrevIdx.get( keyIdxPrevIdx );
				if( mapIdxPrevIdx != null ) {
					mapIdxPrevIdx.remove( keepObj.getPKey() );
					if( mapIdxPrevIdx.size() <= 0 ) {
						indexByIdxPrevIdx.remove( keyIdxPrevIdx );
					}
				}
			}

			if( indexByIdxNextIdx != null ) {
				ICFBamIndexColByIdxNextIdxKey keyIdxNextIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxNextIdxKey();
				keyIdxNextIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyIdxNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIdxNextIdx = indexByIdxNextIdx.get( keyIdxNextIdx );
				if( mapIdxNextIdx != null ) {
					mapIdxNextIdx.remove( keepObj.getPKey() );
					if( mapIdxNextIdx.size() <= 0 ) {
						indexByIdxNextIdx.remove( keyIdxNextIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamIndexColByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByUNameIdxKey();
				keyUNameIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByIndexIdx != null ) {
				ICFBamIndexColByIndexIdxKey keyIndexIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIndexIdxKey();
				keyIndexIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIndexIdx = indexByIndexIdx.get( keyIndexIdx );
				if( mapIndexIdx != null ) {
					mapIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamIndexColByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByColIdx != null ) {
				ICFBamIndexColByColIdxKey keyColIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByColIdxKey();
				keyColIdx.setRequiredColumnId( keepObj.getRequiredColumnId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapColIdx = indexByColIdx.get( keyColIdx );
				if( mapColIdx != null ) {
					mapColIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamIndexColByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamIndexColByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByIdxPrevIdx != null ) {
				ICFBamIndexColByIdxPrevIdxKey keyIdxPrevIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxPrevIdxKey();
				keyIdxPrevIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyIdxPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIdxPrevIdx = indexByIdxPrevIdx.get( keyIdxPrevIdx );
				if( mapIdxPrevIdx != null ) {
					mapIdxPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByIdxNextIdx != null ) {
				ICFBamIndexColByIdxNextIdxKey keyIdxNextIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxNextIdxKey();
				keyIdxNextIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyIdxNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIdxNextIdx = indexByIdxNextIdx.get( keyIdxNextIdx );
				if( mapIdxNextIdx != null ) {
					mapIdxNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allIndexCol != null ) {
				allIndexCol.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allIndexCol != null ) {
				allIndexCol.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamIndexColByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByUNameIdxKey();
				keyUNameIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByIndexIdx != null ) {
				ICFBamIndexColByIndexIdxKey keyIndexIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIndexIdxKey();
				keyIndexIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIndexIdx = indexByIndexIdx.get( keyIndexIdx );
				if( mapIndexIdx != null ) {
					mapIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamIndexColByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByColIdx != null ) {
				ICFBamIndexColByColIdxKey keyColIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByColIdxKey();
				keyColIdx.setRequiredColumnId( keepObj.getRequiredColumnId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapColIdx = indexByColIdx.get( keyColIdx );
				if( mapColIdx != null ) {
					mapColIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamIndexColByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamIndexColByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByIdxPrevIdx != null ) {
				ICFBamIndexColByIdxPrevIdxKey keyIdxPrevIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxPrevIdxKey();
				keyIdxPrevIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyIdxPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIdxPrevIdx = indexByIdxPrevIdx.get( keyIdxPrevIdx );
				if( mapIdxPrevIdx != null ) {
					mapIdxPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByIdxNextIdx != null ) {
				ICFBamIndexColByIdxNextIdxKey keyIdxNextIdx =
					schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxNextIdxKey();
				keyIdxNextIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				keyIdxNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamIndexColObj > mapIdxNextIdx = indexByIdxNextIdx.get( keyIdxNextIdx );
				if( mapIdxNextIdx != null ) {
					mapIdxNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamIndexColObj createIndexCol( ICFBamIndexColObj Obj ) {
		ICFBamIndexColObj obj = Obj;
		ICFBamIndexCol rec = obj.getIndexColRec();
		schema.getCFBamBackingStore().getTableIndexCol().createIndexCol(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		ICFBamIndexColObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamIndexColObj readIndexCol( CFLibDbKeyHash256 pkey ) {
		return( readIndexCol( pkey, false ) );
	}

	@Override
	public ICFBamIndexColObj readIndexCol( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamIndexColObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamIndexCol readRec = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getIndexColTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamIndexColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamIndexColObj readCachedIndexCol( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexColObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeIndexCol( ICFBamIndexColObj obj )
	{
		final String S_ProcName = "CFBamIndexColTableObj.reallyDeepDisposeIndexCol() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamIndexColObj existing = readCachedIndexCol( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamIndexColByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryIndexCol().newByUNameIdxKey();
		keyUNameIdx.setRequiredIndexId( existing.getRequiredIndexId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamIndexColByIndexIdxKey keyIndexIdx = schema.getCFBamBackingStore().getFactoryIndexCol().newByIndexIdxKey();
		keyIndexIdx.setRequiredIndexId( existing.getRequiredIndexId() );

		ICFBamIndexColByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryIndexCol().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamIndexColByColIdxKey keyColIdx = schema.getCFBamBackingStore().getFactoryIndexCol().newByColIdxKey();
		keyColIdx.setRequiredColumnId( existing.getRequiredColumnId() );

		ICFBamIndexColByPrevIdxKey keyPrevIdx = schema.getCFBamBackingStore().getFactoryIndexCol().newByPrevIdxKey();
		keyPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamIndexColByNextIdxKey keyNextIdx = schema.getCFBamBackingStore().getFactoryIndexCol().newByNextIdxKey();
		keyNextIdx.setOptionalNextId( existing.getOptionalNextId() );

		ICFBamIndexColByIdxPrevIdxKey keyIdxPrevIdx = schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxPrevIdxKey();
		keyIdxPrevIdx.setRequiredIndexId( existing.getRequiredIndexId() );
		keyIdxPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamIndexColByIdxNextIdxKey keyIdxNextIdx = schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxNextIdxKey();
		keyIdxNextIdx.setRequiredIndexId( existing.getRequiredIndexId() );
		keyIdxNextIdx.setOptionalNextId( existing.getOptionalNextId() );


					schema.getRelationColTableObj().deepDisposeRelationColByFromColIdx( existing.getRequiredId() );
					schema.getRelationColTableObj().deepDisposeRelationColByToColIdx( existing.getRequiredId() );

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByIndexIdx != null ) {
			if( indexByIndexIdx.containsKey( keyIndexIdx ) ) {
				indexByIndexIdx.get( keyIndexIdx ).remove( pkey );
				if( indexByIndexIdx.get( keyIndexIdx ).size() <= 0 ) {
					indexByIndexIdx.remove( keyIndexIdx );
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

		if( indexByColIdx != null ) {
			if( indexByColIdx.containsKey( keyColIdx ) ) {
				indexByColIdx.get( keyColIdx ).remove( pkey );
				if( indexByColIdx.get( keyColIdx ).size() <= 0 ) {
					indexByColIdx.remove( keyColIdx );
				}
			}
		}

		if( indexByPrevIdx != null ) {
			if( indexByPrevIdx.containsKey( keyPrevIdx ) ) {
				indexByPrevIdx.get( keyPrevIdx ).remove( pkey );
				if( indexByPrevIdx.get( keyPrevIdx ).size() <= 0 ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}
		}

		if( indexByNextIdx != null ) {
			if( indexByNextIdx.containsKey( keyNextIdx ) ) {
				indexByNextIdx.get( keyNextIdx ).remove( pkey );
				if( indexByNextIdx.get( keyNextIdx ).size() <= 0 ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}
		}

		if( indexByIdxPrevIdx != null ) {
			if( indexByIdxPrevIdx.containsKey( keyIdxPrevIdx ) ) {
				indexByIdxPrevIdx.get( keyIdxPrevIdx ).remove( pkey );
				if( indexByIdxPrevIdx.get( keyIdxPrevIdx ).size() <= 0 ) {
					indexByIdxPrevIdx.remove( keyIdxPrevIdx );
				}
			}
		}

		if( indexByIdxNextIdx != null ) {
			if( indexByIdxNextIdx.containsKey( keyIdxNextIdx ) ) {
				indexByIdxNextIdx.get( keyIdxNextIdx ).remove( pkey );
				if( indexByIdxNextIdx.get( keyIdxNextIdx ).size() <= 0 ) {
					indexByIdxNextIdx.remove( keyIdxNextIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeIndexCol( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexColObj obj = readCachedIndexCol( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamIndexColObj lockIndexCol( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexColObj locked = null;
		ICFBamIndexCol lockRec = schema.getCFBamBackingStore().getTableIndexCol().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getIndexColTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamIndexColObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockIndexCol", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamIndexColObj> readAllIndexCol() {
		return( readAllIndexCol( false ) );
	}

	@Override
	public List<ICFBamIndexColObj> readAllIndexCol( boolean forceRead ) {
		final String S_ProcName = "readAllIndexCol";
		if( ( allIndexCol == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> map = new HashMap<CFLibDbKeyHash256,ICFBamIndexColObj>();
			allIndexCol = map;
			ICFBamIndexCol[] recList = schema.getCFBamBackingStore().getTableIndexCol().readAllDerived( null );
			ICFBamIndexCol rec;
			ICFBamIndexColObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexColObj realised = (ICFBamIndexColObj)obj.realise();
			}
		}
		int len = allIndexCol.size();
		ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
		Iterator<ICFBamIndexColObj> valIter = allIndexCol.values().iterator();
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
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
		List<ICFBamIndexColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexColObj> readCachedAllIndexCol() {
		final String S_ProcName = "readCachedAllIndexCol";
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>();
		if( allIndexCol != null ) {
			int len = allIndexCol.size();
			ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
			Iterator<ICFBamIndexColObj> valIter = allIndexCol.values().iterator();
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
		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
	public ICFBamIndexColObj readIndexColByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readIndexColByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamIndexColObj readIndexColByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamIndexColObj obj = readIndexCol( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamIndexColObj readIndexColByUNameIdx( CFLibDbKeyHash256 IndexId,
		String Name )
	{
		return( readIndexColByUNameIdx( IndexId,
			Name,
			false ) );
	}

	@Override
	public ICFBamIndexColObj readIndexColByUNameIdx( CFLibDbKeyHash256 IndexId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamIndexColByUNameIdxKey,
				ICFBamIndexColObj >();
		}
		ICFBamIndexColByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByUNameIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setRequiredName( Name );
		ICFBamIndexColObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamIndexCol rec = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByUNameIdx( null,
				IndexId,
				Name );
			if( rec != null ) {
				obj = schema.getIndexColTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamIndexColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByIndexIdx( CFLibDbKeyHash256 IndexId )
	{
		return( readIndexColByIndexIdx( IndexId,
			false ) );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByIndexIdx( CFLibDbKeyHash256 IndexId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexColByIndexIdx";
		ICFBamIndexColByIndexIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIndexIdxKey();
		key.setRequiredIndexId( IndexId );
		Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
		if( indexByIndexIdx == null ) {
			indexByIndexIdx = new HashMap< ICFBamIndexColByIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( ( ! forceRead ) && indexByIndexIdx.containsKey( key ) ) {
			dict = indexByIndexIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
			ICFBamIndexColObj obj;
			ICFBamIndexCol[] recList = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByIndexIdx( null,
				IndexId );
			ICFBamIndexCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getIndexColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexColObj realised = (ICFBamIndexColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByIndexIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
		Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
		List<ICFBamIndexColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readIndexColByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexColByDefSchemaIdx";
		ICFBamIndexColByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamIndexColByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
			ICFBamIndexColObj obj;
			ICFBamIndexCol[] recList = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamIndexCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getIndexColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexColObj realised = (ICFBamIndexColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
		Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
		List<ICFBamIndexColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByColIdx( CFLibDbKeyHash256 ColumnId )
	{
		return( readIndexColByColIdx( ColumnId,
			false ) );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByColIdx( CFLibDbKeyHash256 ColumnId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexColByColIdx";
		ICFBamIndexColByColIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByColIdxKey();
		key.setRequiredColumnId( ColumnId );
		Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
		if( indexByColIdx == null ) {
			indexByColIdx = new HashMap< ICFBamIndexColByColIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( ( ! forceRead ) && indexByColIdx.containsKey( key ) ) {
			dict = indexByColIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
			ICFBamIndexColObj obj;
			ICFBamIndexCol[] recList = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByColIdx( null,
				ColumnId );
			ICFBamIndexCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getIndexColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexColObj realised = (ICFBamIndexColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByColIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
		Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
		List<ICFBamIndexColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readIndexColByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexColByPrevIdx";
		ICFBamIndexColByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamIndexColByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
			ICFBamIndexColObj obj;
			ICFBamIndexCol[] recList = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamIndexCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getIndexColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexColObj realised = (ICFBamIndexColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
		Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
		List<ICFBamIndexColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readIndexColByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexColByNextIdx";
		ICFBamIndexColByNextIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamIndexColByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
			ICFBamIndexColObj obj;
			ICFBamIndexCol[] recList = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByNextIdx( null,
				NextId );
			ICFBamIndexCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getIndexColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexColObj realised = (ICFBamIndexColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
		Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
		List<ICFBamIndexColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readIndexColByIdxPrevIdx( IndexId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexColByIdxPrevIdx";
		ICFBamIndexColByIdxPrevIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxPrevIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
		if( indexByIdxPrevIdx == null ) {
			indexByIdxPrevIdx = new HashMap< ICFBamIndexColByIdxPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( ( ! forceRead ) && indexByIdxPrevIdx.containsKey( key ) ) {
			dict = indexByIdxPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
			ICFBamIndexColObj obj;
			ICFBamIndexCol[] recList = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByIdxPrevIdx( null,
				IndexId,
				PrevId );
			ICFBamIndexCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getIndexColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexColObj realised = (ICFBamIndexColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByIdxPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
		Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
		List<ICFBamIndexColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId )
	{
		return( readIndexColByIdxNextIdx( IndexId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamIndexColObj> readIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexColByIdxNextIdx";
		ICFBamIndexColByIdxNextIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxNextIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
		if( indexByIdxNextIdx == null ) {
			indexByIdxNextIdx = new HashMap< ICFBamIndexColByIdxNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( ( ! forceRead ) && indexByIdxNextIdx.containsKey( key ) ) {
			dict = indexByIdxNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexColObj>();
			ICFBamIndexColObj obj;
			ICFBamIndexCol[] recList = schema.getCFBamBackingStore().getTableIndexCol().readDerivedByIdxNextIdx( null,
				IndexId,
				NextId );
			ICFBamIndexCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getIndexColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexColObj realised = (ICFBamIndexColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByIdxNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
		Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
		List<ICFBamIndexColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamIndexColObj readCachedIndexColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexColObj obj = null;
		obj = readCachedIndexCol( Id );
		return( obj );
	}

	@Override
	public ICFBamIndexColObj readCachedIndexColByUNameIdx( CFLibDbKeyHash256 IndexId,
		String Name )
	{
		ICFBamIndexColObj obj = null;
		ICFBamIndexColByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByUNameIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
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
			Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
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
	public List<ICFBamIndexColObj> readCachedIndexColByIndexIdx( CFLibDbKeyHash256 IndexId )
	{
		final String S_ProcName = "readCachedIndexColByIndexIdx";
		ICFBamIndexColByIndexIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIndexIdxKey();
		key.setRequiredIndexId( IndexId );
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>();
		if( indexByIndexIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
			if( indexByIndexIdx.containsKey( key ) ) {
				dict = indexByIndexIdx.get( key );
				int len = dict.size();
				ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
				Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
			ICFBamIndexColObj obj;
			Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
	public List<ICFBamIndexColObj> readCachedIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedIndexColByDefSchemaIdx";
		ICFBamIndexColByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
				Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
			ICFBamIndexColObj obj;
			Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
	public List<ICFBamIndexColObj> readCachedIndexColByColIdx( CFLibDbKeyHash256 ColumnId )
	{
		final String S_ProcName = "readCachedIndexColByColIdx";
		ICFBamIndexColByColIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByColIdxKey();
		key.setRequiredColumnId( ColumnId );
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>();
		if( indexByColIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
			if( indexByColIdx.containsKey( key ) ) {
				dict = indexByColIdx.get( key );
				int len = dict.size();
				ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
				Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
			ICFBamIndexColObj obj;
			Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
	public List<ICFBamIndexColObj> readCachedIndexColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedIndexColByPrevIdx";
		ICFBamIndexColByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
				Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
			ICFBamIndexColObj obj;
			Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
	public List<ICFBamIndexColObj> readCachedIndexColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedIndexColByNextIdx";
		ICFBamIndexColByNextIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
				Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
			ICFBamIndexColObj obj;
			Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
	public List<ICFBamIndexColObj> readCachedIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedIndexColByIdxPrevIdx";
		ICFBamIndexColByIdxPrevIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxPrevIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>();
		if( indexByIdxPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
			if( indexByIdxPrevIdx.containsKey( key ) ) {
				dict = indexByIdxPrevIdx.get( key );
				int len = dict.size();
				ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
				Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
			ICFBamIndexColObj obj;
			Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
	public List<ICFBamIndexColObj> readCachedIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedIndexColByIdxNextIdx";
		ICFBamIndexColByIdxNextIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxNextIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamIndexColObj> arrayList = new ArrayList<ICFBamIndexColObj>();
		if( indexByIdxNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict;
			if( indexByIdxNextIdx.containsKey( key ) ) {
				dict = indexByIdxNextIdx.get( key );
				int len = dict.size();
				ICFBamIndexColObj arr[] = new ICFBamIndexColObj[len];
				Iterator<ICFBamIndexColObj> valIter = dict.values().iterator();
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
			ICFBamIndexColObj obj;
			Iterator<ICFBamIndexColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexColObj> cmp = new Comparator<ICFBamIndexColObj>() {
			@Override
			public int compare( ICFBamIndexColObj lhs, ICFBamIndexColObj rhs ) {
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
	public void deepDisposeIndexColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexColObj obj = readCachedIndexColByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeIndexColByUNameIdx( CFLibDbKeyHash256 IndexId,
		String Name )
	{
		ICFBamIndexColObj obj = readCachedIndexColByUNameIdx( IndexId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeIndexColByIndexIdx( CFLibDbKeyHash256 IndexId )
	{
		final String S_ProcName = "deepDisposeIndexColByIndexIdx";
		ICFBamIndexColObj obj;
		List<ICFBamIndexColObj> arrayList = readCachedIndexColByIndexIdx( IndexId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeIndexColByDefSchemaIdx";
		ICFBamIndexColObj obj;
		List<ICFBamIndexColObj> arrayList = readCachedIndexColByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexColByColIdx( CFLibDbKeyHash256 ColumnId )
	{
		final String S_ProcName = "deepDisposeIndexColByColIdx";
		ICFBamIndexColObj obj;
		List<ICFBamIndexColObj> arrayList = readCachedIndexColByColIdx( ColumnId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeIndexColByPrevIdx";
		ICFBamIndexColObj obj;
		List<ICFBamIndexColObj> arrayList = readCachedIndexColByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeIndexColByNextIdx";
		ICFBamIndexColObj obj;
		List<ICFBamIndexColObj> arrayList = readCachedIndexColByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeIndexColByIdxPrevIdx";
		ICFBamIndexColObj obj;
		List<ICFBamIndexColObj> arrayList = readCachedIndexColByIdxPrevIdx( IndexId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeIndexColByIdxNextIdx";
		ICFBamIndexColObj obj;
		List<ICFBamIndexColObj> arrayList = readCachedIndexColByIdxNextIdx( IndexId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamIndexColObj updateIndexCol( ICFBamIndexColObj Obj ) {
		ICFBamIndexColObj obj = Obj;
		schema.getCFBamBackingStore().getTableIndexCol().updateIndexCol( null,
			Obj.getIndexColRec() );
		obj = (ICFBamIndexColObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteIndexCol( ICFBamIndexColObj Obj ) {
		ICFBamIndexColObj obj = Obj;
		ICFBamIndexColObj prev = obj.getOptionalLookupPrev();
		ICFBamIndexColObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableIndexCol().deleteIndexCol( null,
			obj.getIndexColRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteIndexColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexColObj obj = readIndexCol(Id);
		if( obj != null ) {
			ICFBamIndexColEditObj editObj = (ICFBamIndexColEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamIndexColEditObj)obj.beginEdit();
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
		deepDisposeIndexColByIdIdx( Id );
	}

	@Override
	public void deleteIndexColByUNameIdx( CFLibDbKeyHash256 IndexId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamIndexColByUNameIdxKey,
				ICFBamIndexColObj >();
		}
		ICFBamIndexColByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByUNameIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setRequiredName( Name );
		ICFBamIndexColObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByUNameIdx( null,
				IndexId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByUNameIdx( null,
				IndexId,
				Name );
		}
		deepDisposeIndexColByUNameIdx( IndexId,
				Name );
	}

	@Override
	public void deleteIndexColByIndexIdx( CFLibDbKeyHash256 IndexId )
	{
		ICFBamIndexColByIndexIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIndexIdxKey();
		key.setRequiredIndexId( IndexId );
		if( indexByIndexIdx == null ) {
			indexByIndexIdx = new HashMap< ICFBamIndexColByIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( indexByIndexIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict = indexByIndexIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByIndexIdx( null,
				IndexId );
			Iterator<ICFBamIndexColObj> iter = dict.values().iterator();
			ICFBamIndexColObj obj;
			List<ICFBamIndexColObj> toForget = new LinkedList<ICFBamIndexColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByIndexIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByIndexIdx( null,
				IndexId );
		}
		deepDisposeIndexColByIndexIdx( IndexId );
	}

	@Override
	public void deleteIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamIndexColByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamIndexColByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamIndexColObj> iter = dict.values().iterator();
			ICFBamIndexColObj obj;
			List<ICFBamIndexColObj> toForget = new LinkedList<ICFBamIndexColObj>();
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
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeIndexColByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteIndexColByColIdx( CFLibDbKeyHash256 ColumnId )
	{
		ICFBamIndexColByColIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByColIdxKey();
		key.setRequiredColumnId( ColumnId );
		if( indexByColIdx == null ) {
			indexByColIdx = new HashMap< ICFBamIndexColByColIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( indexByColIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict = indexByColIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByColIdx( null,
				ColumnId );
			Iterator<ICFBamIndexColObj> iter = dict.values().iterator();
			ICFBamIndexColObj obj;
			List<ICFBamIndexColObj> toForget = new LinkedList<ICFBamIndexColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByColIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByColIdx( null,
				ColumnId );
		}
		deepDisposeIndexColByColIdx( ColumnId );
	}

	@Override
	public void deleteIndexColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamIndexColByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamIndexColByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByPrevIdx( null,
				PrevId );
			Iterator<ICFBamIndexColObj> iter = dict.values().iterator();
			ICFBamIndexColObj obj;
			List<ICFBamIndexColObj> toForget = new LinkedList<ICFBamIndexColObj>();
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
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByPrevIdx( null,
				PrevId );
		}
		deepDisposeIndexColByPrevIdx( PrevId );
	}

	@Override
	public void deleteIndexColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamIndexColByNextIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamIndexColByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByNextIdx( null,
				NextId );
			Iterator<ICFBamIndexColObj> iter = dict.values().iterator();
			ICFBamIndexColObj obj;
			List<ICFBamIndexColObj> toForget = new LinkedList<ICFBamIndexColObj>();
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
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByNextIdx( null,
				NextId );
		}
		deepDisposeIndexColByNextIdx( NextId );
	}

	@Override
	public void deleteIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamIndexColByIdxPrevIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxPrevIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setOptionalPrevId( PrevId );
		if( indexByIdxPrevIdx == null ) {
			indexByIdxPrevIdx = new HashMap< ICFBamIndexColByIdxPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( indexByIdxPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict = indexByIdxPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByIdxPrevIdx( null,
				IndexId,
				PrevId );
			Iterator<ICFBamIndexColObj> iter = dict.values().iterator();
			ICFBamIndexColObj obj;
			List<ICFBamIndexColObj> toForget = new LinkedList<ICFBamIndexColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByIdxPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByIdxPrevIdx( null,
				IndexId,
				PrevId );
		}
		deepDisposeIndexColByIdxPrevIdx( IndexId,
				PrevId );
	}

	@Override
	public void deleteIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamIndexColByIdxNextIdxKey key = schema.getCFBamBackingStore().getFactoryIndexCol().newByIdxNextIdxKey();
		key.setRequiredIndexId( IndexId );
		key.setOptionalNextId( NextId );
		if( indexByIdxNextIdx == null ) {
			indexByIdxNextIdx = new HashMap< ICFBamIndexColByIdxNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexColObj > >();
		}
		if( indexByIdxNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexColObj> dict = indexByIdxNextIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByIdxNextIdx( null,
				IndexId,
				NextId );
			Iterator<ICFBamIndexColObj> iter = dict.values().iterator();
			ICFBamIndexColObj obj;
			List<ICFBamIndexColObj> toForget = new LinkedList<ICFBamIndexColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByIdxNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndexCol().deleteIndexColByIdxNextIdx( null,
				IndexId,
				NextId );
		}
		deepDisposeIndexColByIdxNextIdx( IndexId,
				NextId );
	}

	/**
	 *	Move the CFBamIndexColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamIndexColObj refreshed cache instance.
	 */
	@Override
	public ICFBamIndexColObj moveUpIndexCol( ICFBamIndexColObj Obj ) {
		ICFBamIndexColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpIndexCol" );
		}
		ICFBamIndexCol rec = schema.getCFBamBackingStore().getTableIndexCol().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getIndexColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamIndexColObj)obj.realise();
			ICFBamIndexColObj prev = obj.getOptionalLookupPrev( true );
			ICFBamIndexColObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamIndexColObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamIndexColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamIndexColObj refreshed cache instance.
	 */
	@Override
	public ICFBamIndexColObj moveDownIndexCol( ICFBamIndexColObj Obj ) {
		ICFBamIndexColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownIndexCol" );
		}
		ICFBamIndexCol rec = schema.getCFBamBackingStore().getTableIndexCol().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getIndexColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamIndexColObj)obj.realise();
			ICFBamIndexColObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamIndexColObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamIndexColObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}